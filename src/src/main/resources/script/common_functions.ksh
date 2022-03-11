#!/usr/bin/ksh

checkResult () {

if [ $1 -eq 0 ] ; then
    print $2 | tee -a $log_file
    return 0
else
    print $3 | tee -a $log_file
    return 1
fi

}


getTime () {
now=$(date)
echo $now
}


log () {
if [[ ! -z $1 ]] && [[ $1 = *[^[:space:]]* ]] ; then
level=${2:-0}
if [[ $level = 0 ]] ; then
   echo $1 >> $log_file
elif [[ $level = 1 ]] ; then
   echo [$(getTime)]: $1 >> $log_file
elif [[ $level = 2 ]] ; then
   echo "   * "$1 >> $log_file
else
   echo $1 >> $log_file
fi
fi
}

compareVersions ()
{
  currentIFS=$IFS
  IFS='.'
  set -A v1 $1
  set -A v2 $2
  typeset n diff
  n=0
  limit=0
  bias=0
  if [ ${#v1[@]} -gt ${#v2[@]} ]; then
	limit=${#v2[@]}
	bias=1
  elif [ ${#v1[@]} -lt ${#v2[@]} ]; then
	limit=${#v1[@]}
	bias=-1
  else
	limit=${#v1[@]}
      	bias=0
  fi
  while [[ $n -lt $limit ]] ; do
   diff=$((v1[n]-v2[n]))
    if [ $diff -ne 0 ] ; then
      [ $diff -le 0 ] && echo '-1' || echo '1'
      return
    fi
   n=$(expr $n + 1)
  done
  echo $bias
  IFS=${currentIFS}
}

runCommand()
{
	cmd="$1"
	result=$($cmd)
	checkResult $? "ok " "Error"
	log "$result" 2
}

getSQLDirectory()
{
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    if [ -d "${sashome}/sql/oracle" ]; then
      sqlScriptDir="${sashome}/sql/oracle"
    else
      sqlScriptDir="${sashome}/sql"
    fi
  else
    sqlScriptDir="${sashome}/sql/postgres"
  fi
}