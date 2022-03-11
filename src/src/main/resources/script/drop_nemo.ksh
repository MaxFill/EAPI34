#!/usr/bin/ksh

project_type=java
product_name=EAPI
product_version=33
ne_type=EAPI

. $SASBASE/bin/help_functions.ksh
. ./common_functions.ksh
. ./il_nemo_functions.ksh

get_dbinfo
oracle_sid=\'$TWO_TASK\'
ne_id=${ne_id}
script_dir_base="$SASBASE/bin/macro_server/$project_type/$ne_type/$product_name/$product_version/script"
log_file="$script_dir_base/nemo_setup.log"
nemo_info="$script_dir_base/nemo_info.txt"


	currentIFS=$IFS
	set -A nemoList $(cat ./nemo_info.txt)
	if [ ${#nemoList[@]} -eq 0 ] ; then
	print "There are no configured nemo to remove" 
	exit
	fi
	print "Configured Nemo:"
	echo "0.Exit"
	IFS='|'
	i=1
	while [[ $i -le ${#nemoList[*]} ]] ; do
		 temp="${nemoList[$(expr $i-1)]}"
		 set -A temp2 $temp
		 listneid[$i]=${temp2[0]}
		 listnetype[$i]=${temp2[1]}
		 listconnectionnum[$i]=${temp2[2]}
		 echo "$i.${listneid[$i]}"
		 ((i+=1))
	done
	IFS=${currentIFS}
print "Please select the nemo configuration to remove (enter 0 to exit):"	
selected_nemo=
while [ 0 ] ; do
read selected_nemo
if [[ ! "$selected_nemo" = ?(+|-)+([0-9]) ]] ; then
	print "Please enter a valid selection"
elif [ "$selected_nemo" -eq 0 ] ; then
	exit
elif [ "$selected_nemo" -lt 1 ] || [ "$selected_nemo" -gt "${#nemoList[*]}" ] ; then
	print "Please enter a valid selection"
else
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    network_element_type_count=`$ORACLE_HOME/bin/sqlplus -L -S $DB_USER/$DB_PASS@$oracle_sid <<EOF
    set pagesize 0
    whenever sqlerror exit 1
    whenever oserror  exit 1
    SELECT trim(count(*)) AS ELEMENT_COUNT
    from nemo_ne_type
    where ne_type = '$network_element_type' AND version = 0;
    exit
EOF
    `
  else
    export PGPASSWORD=${DB_PASS}
    network_element_type_count=`$PSQL_HOME/bin/psql ${DB_URL} --username ${DB_USER} -v ON_ERROR_STOP=1 --tuples-only <<EOF
    \set pagesize 0
    \set ON_ERROR_STOP true
    SELECT count(*) AS ELEMENT_COUNT
    from nemo_ne_type
    where ne_type = '$network_element_type' AND version = 0;
    \q
EOF
    `
  fi
  
if [ "$(print "$network_element_type_count" | sed -e 's| ||g')" = "0" ]; then
    ne_found=0
else
   ne_found=1
fi



selectedneid="${listneid[$selected_nemo]}"
if [ $ne_found = "0" ] ; then
	selectednetype="${listnetype[$selected_nemo]}"
else
	selectednetype=""
fi
selectedconnectionnum="${listconnectionnum[$selected_nemo]}"
selectedconnectionid=ilink_"$selectedneid"_"$selectedconnectionnum"
break
fi
done

print "Confirm to remove the auto configured network configuration for $selectedneid (yes/no)?:"
remove_response=
 while [ 0 ] ; do
      read remove_response
      if [ "$remove_response" = "no" ] || [ "$remove_response" = "n" ] ; then
          print "Exiting."
          exit 14
          break
      elif [ "$remove_response" = "yes" ] || [ "$remove_response" = "y" ] ; then
		log "START OF SCRIPT (NEMO Removal)" 1
		remove_autoconfig_nemo "$selectedneid" "$selectednetype" "$selectedconnectionnum" "$selectedconnectionid"
		unset nemoList[$selected_nemo-1]
		set -A temp ${nemoList[@]}
		> $nemo_info
		i=0
		while [[ $i -lt ${#temp[*]} ]] ; do
		 echo "${temp[$i]}" >> $nemo_info
		 ((i+=1))
		done
		
		if [ $success = 1 ] ; then
			print ""
			print "Network configurations for $selectedneid successfully removed" | tee -a $log_file
		else
			print "There were errors while removing the network model. Please refer to the nemo_setup.log for details."	
			print "There were errors while removing the network model" >> $log_file
		fi
		
		do_reconfig=1
if [ "$do_reconfig" = 1 ] ; then
  print "\nWould you like to apply the new changes (yes/no)?:"
  reconfig_response=
  while [ 0 ] ; do
      read reconfig_response
      if [ "$reconfig_response" = "no" ] || [ "$reconfig_response" = "n" ] ; then
          print "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
          print "+                                              IMPORTANT NOTE                                                        +"
          print "+     IN ORDER TO APPLY THE CURRENT SETUP CHANGES, PLEASE RESTART INSTANTLINK OR RUN  ctl_control -f reconfig        +"
          print "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
          print "Exiting."
          break
      elif [ "$reconfig_response" = "yes" ] || [ "$reconfig_response" = "y" ] ; then
          reconfig_response="yes"
		  print "Reconfiguring InstantLink...." | tee -a $log_file
          ctl_control -f reconfig | tee -a $log_file
          checkResult "$?" "Reconfiguring process completed" "Error: reconfig failed!"
          break
      fi

      print "Please answer either yes (y) or no (n)"
  done
fi
		log "END OF SCRIPT (NEMO Removal)" 1
		print | tee -a $log_file
          	break
      fi
      print "Please answer either yes (y) or no (n)"
  done
    