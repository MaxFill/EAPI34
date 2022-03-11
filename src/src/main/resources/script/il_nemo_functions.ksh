#!/usr/bin/ksh
success=1

insert_ne_type () {
    print -n "Adding network element type.." | tee -a $log_file
    if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then     
      cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_type.sql '"$1"' '"$2"' '"$3"' '"$4"' 'Auto Configuration Script'"
      result=$($cmd)
    else
      result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NE_TYPE="$1" -v NE_TYPE_DESCRIPTION="$2" -v PRIM_TARGET_ID="$3" -v SEC_TARGET_ID="$4" -v CHANGED_BY="Auto Configuration Script" -f "$sqlScriptDir/insert_ne_type.sql" )
    fi
    checkResult $? "ok" "Error when inserting ne type"
    if [ $? != 0 ]; then
    	success=0
    fi
    #log "$cmd" 2
    log "$result" 2
}


insert_nei_type () {
  print -n "Creating new network element interface.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_connection_control.sql '"$1"' 1 '"$2"' ' ' 'Auto Configuration Script'"
    result=$($cmd)
  else
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v CONNECTION_TYPE="$1" -v CONNECTION_USAGE="1" -v CONNECTION_CMD="$2" -v CONNECTION_PROTOCOL=" "  -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_connection_control.sql" )
  fi

  checkResult $? "ok" "Error when inserting to ne connection control"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}

insert_ne () {
  print -n "Inserting network element.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne.sql '"$1"' '"$2"' '"$3"' '"$4"' '"$5"' '"$6"' '"$7"' 0 'Auto Configuration Script'"
    result=$($cmd)
  else
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NE_ID="$1" -v NE_DESCRIPTION="$2" -v NE_TYPE="$3" -v NE_CATEGORY="$4" -v PRODUCT_NAME="$5" -v PRODUCT_VERSION="$6" -v MAX_CONNECTION_WAIT_TIME="$7" -v NE_STATE="0" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne.sql" )
  fi

  checkResult $? "ok" "Error when inserting nemo network element"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}

insert_nemo_connection () {
  print -n "Inserting network model connection.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_nemo_connection.sql '"ilink_$1_$2"' ilink '"$1"' '"$2"' '"$3"' '"$4"' '"$5"' '"$6"' '"$7"' '"$8"' '"$9"' '"${10}"' '"${11}"' '"${12}"' '"${13}"' 0 'Auto Configuration Script'"
    result=$($cmd)
  else 
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v CONNECTION_ID="ilink_$1_$2" -v SOURCE_NE="ilink" -v TARGET_NE="$1" -v CONNECTION_NO="$2" -v CONNECTION_TYPE="$3" -v CMD_ARGUMENTS="$4" -v CMD_ADDRESS="$5" -v USER_ID="$6" -v USER_PASSWD="$7" -v MAX_CHANNELS="$8" -v PRIORITY="$9" -v LOGIN_RETRY_MAX="${10}" -v LOGIN_RETRY_SLEEP="${11}" -v LOGOUT_LINGER="${12}" -v MS_TYPE="${13}" -v CONNECTION_STATE="0" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_nemo_connection.sql" )
  fi
  
  checkResult $? "ok" "Error when inserting to network model connection"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}


insert_ne_in () {
  print -n "Inserting network element in-network(ilink_$1_$2).." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_in_network.sql SAS '"ilink_$1_$2"' 'Auto Configuration Script'"
    result=$($cmd)
  else 
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="ilink_$1_$2" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_in_network.sql" )
  fi

  checkResult $? "ok" "Error when inserting to ne in-network"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2


  print -n "Inserting network element in-network($1).." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_in_network.sql SAS '"$1"' 'Auto Configuration Script'"
    result=$($cmd)
  else
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_in_network.sql" )
  fi

  checkResult $? "ok" "Error when inserting to ne in network"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}


insert_default_ne_parameter () {
  print -n "Inserting default parameters.." | tee -a $log_file
  print | tee -a $log_file

  print -n "AUTO_CHECK.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 AUTO_CHECK NO 'taskengine' 'Defines whether the connection to the NE is periodically checked when the connection is idle. YES/NO' 'Auto Configuration Script'"
    result=$($cmd)
  else
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="AUTO_CHECK" -v PARAM_VALUE="NO" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines whether the connection to the NE is periodically checked when the connection is idle. YES/NO" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi
  
  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "AUTO_CHECK_INTERVAL.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 AUTO_CHECK_INTERVAL 900 'taskengine' 'Defines in seconds, how often the connection to the NE is checked.' 'Auto Configuration Script'"
    result=$($cmd)
  else
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="AUTO_CHECK_INTERVAL" -v PARAM_VALUE="900" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines in seconds, how often the connection to the NE is checked." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi
  
  checkResult $? "ok" "Error"
  #log "$cmd" 2
  log "$result" 2

  print -n "COMMAND_DELAY.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 COMMAND_DELAY 0 'taskengine' 'Defines the sleep time in seconds before a MML command is sent to the NE.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="COMMAND_DELAY" -v PARAM_VALUE="0" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the sleep time in seconds before a MML command is sent to the NE." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "LOGIN_MML_LOG_INDICATOR.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 LOGIN_MML_LOG_INDICATOR YES 'taskengine' 'Defines whether a MML login dialog is written to a log file. YES/NO' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="LOGIN_MML_LOG_INDICATOR" -v PARAM_VALUE="YES" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines whether a MML login dialog is written to a log file. YES/NO" -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "LOWER_ALARM_THRESHOLD.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 LOWER_ALARM_THRESHOLD 100 'taskengine' 'Defines the queue length limit that triggers the alarm QUEUE_LENGTH_LOWER_NOTIFICATION for each SMEs separately.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="LOWER_ALARM_THRESHOLD" -v PARAM_VALUE="100" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the queue length limit that triggers the alarm QUEUE_LENGTH_LOWER_NOTIFICATION for each SMEs separately." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "MAX_QUEUE_LENGTH.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 MAX_QUEUE_LENGTH 100 'taskengine' 'Defines the maximum length of the NE-specific task queue in Task Engine.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="MAX_QUEUE_LENGTH" -v PARAM_VALUE="100" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the maximum length of the NE-specific task queue in Task Engine." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "MAX_SESSIONS.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 MAX_SESSIONS 20 'taskengine' 'Defines the maximum number of simultaneous connections to the NE.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="MAX_SESSIONS" -v PARAM_VALUE="20" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the maximum number of simultaneous connections to the NE." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi
  
  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "MML_RESP_WAIT_TIME.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 MML_RESP_WAIT_TIME 20 'taskengine' 'Defines the maximum time in seconds to wait for a MML response from the NE.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="MML_RESP_WAIT_TIME" -v PARAM_VALUE="20" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the maximum time in seconds to wait for a MML response from the NE." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "MS_RESPONSE_WAIT_TIME.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 MS_RESPONSE_WAIT_TIME 30 'taskengine' 'Defines the maximum time in seconds to wait for a reply from macro server.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="MS_RESPONSE_WAIT_TIME" -v PARAM_VALUE="30" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the maximum time in seconds to wait for a reply from macro server." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "QUEUE_ENABLE_THRESHOLD.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 QUEUE_ENABLE_THRESHOLD 20 'taskengine' 'Defines the threshold size of Task Engines NE-specific task queue when task sending is re-enabled at Request Engine.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="QUEUE_ENABLE_THRESHOLD" -v PARAM_VALUE=20 -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the threshold size of Task Engines NE-specific task queue when task sending is re-enabled at Request Engine." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "QUEUE_LENGTH_THRESHOLD.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 QUEUE_LENGTH_THRESHOLD 10 'taskengine' 'Defines the length of Task Engines NE-specific task queue before a new session created.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="QUEUE_LENGTH_THRESHOLD" -v PARAM_VALUE="10" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the length of Task Engines NE-specific task queue before a new session created." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "TASK_MML_LOG_INDICATOR.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 TASK_MML_LOG_INDICATOR YES 'taskengine' 'Defines whether a MML dialog is written to a log file. YES/NO.' 'Auto Configuration Script'"
    result=$($cmd)
  else     
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="TASK_MML_LOG_INDICATOR" -v PARAM_VALUE="YES" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines whether a MML dialog is written to a log file. YES/NO." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "TIME_WINDOWS.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 TIME_WINDOWS \''[5 * * * *][15 * * * *],[25 * * * *][35 * * * *],[45 * * * *][55 * * * *]'\' 'taskengine' 'Defines time windows for the NE.' 'Auto Configuration Script'"
    result=$(eval "$cmd")
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="TIME_WINDOWS" -v PARAM_VALUE="[5 * * * *][15 * * * *],[25 * * * *][35 * * * *],[45 * * * *][55 * * * *]" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines time windows for the NE." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  
  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "TIME_WINDOWS_MEAN.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 TIME_WINDOWS_MEAN DOWN-TIME 'taskengine' 'Specifies whether the time windows define UP-TIME or DOWN-TIME behaviour.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="TIME_WINDOWS_MEAN" -v PARAM_VALUE="DOWN-TIME" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Specifies whether the time windows define UP-TIME or DOWN-TIME behaviour." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "UPPER_ALARM_THRESHOLD.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 UPPER_ALARM_THRESHOLD 200 'taskengine' 'Defines the queue length limit that triggers the alarm QUEUE_LENGTH_UPPER_ALARM for each SMEs separately.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="UPPER_ALARM_THRESHOLD" -v PARAM_VALUE="200" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines the queue length limit that triggers the alarm QUEUE_LENGTH_UPPER_ALARM for each SMEs separately." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi
 
  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2

  print -n "USE_TIME_WINDOWS.." | tee -a $log_file
  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @$sqlScriptDir/insert_ne_parameter.sql SAS $1 USE_TIME_WINDOWS NO 'taskengine' 'Defines whether the time windows functionality is used. Possible values are YES/NO.' 'Auto Configuration Script'"
    result=$($cmd)
  else    
    result=$($PSQL_HOME/bin/psql "${DB_URL}" --username "${DB_USER}" -v ON_ERROR_STOP=1 -v NETWORK_ID="SAS" -v NE_ID="$1" -v PARAM_NAME="USE_TIME_WINDOWS" -v PARAM_VALUE="NO" -v PROGRAM_NAME="taskengine" -v DESCRIPTION="Defines whether the time windows functionality is used. Possible values are YES/NO." -v CHANGED_BY="Auto Configuration Script" --tuples-only -f "$sqlScriptDir/insert_ne_parameter.sql" )
  fi

  checkResult $? "ok" "Error"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}

remove_autoconfig_nemo() {
  print -n "Removing auto configured network connection($1).." | tee -a $log_file

  if [ "$DATABASE" = "oracle" ] || [ "$DATABASE" = "" ]; then
    cmd="$ORACLE_HOME/bin/sqlplus -L -S ${DB_USER}/${DB_PASS}@$oracle_sid @${script_dir_base}/oracle/drop_nemo.sql $1 $2 $3 $4"
  else
    cmd="$PSQL_HOME/bin/psql ${DB_URL} --username ${DB_USER} -v ON_ERROR_STOP=1 -v v1='$1' -v v2='$2' -v v3='$3' -v v4='$4' --tuples-only -f ${script_dir_base}/postgres/drop_nemo.sql"
  fi
  result=$($cmd)
  checkResult $? "ok" "Error when removing network configuration"
  if [ $? != 0 ]; then
    success=0
  fi
  #log "$cmd" 2
  log "$result" 2
}