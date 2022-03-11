#!/usr/bin/ksh

# <general description of NEI>
#
# This NEI supports Java 8 with Java Macro Server version 9.1.1.

# User Configurable Parameters
MIN_HEAP_SIZE=16m
MAX_HEAP_SIZE=64m


installdir=__BASEDIR__
jmsdir=${installdir}/sas/bin/macro_server/java

# Use Java JRE8 that is currently in use in IL 9.0 environment
export JAVA_HOME=${installdir}/java_jre/current

# Use Java Macro Server compiled with JDK8
jms_jar=${jmsdir}/java_macroserver-JDK8.jar


CLASSPATH=${jmsdir}/EAPI/EAPI/34/macro_EAPI_EAPI_34.jar:${jms_jar}
## REMARK: This section will be updated by SDK from "Update Dependencies to NEI Package" option. ##
CLASSPATH=$CLASSPATH
## END REMARK ##

exec $JAVA_HOME/bin/java -Xms$MIN_HEAP_SIZE -Xmx$MAX_HEAP_SIZE $1 $2 -cp $CLASSPATH com.comptel.mds.sas.java_macroserver.MacroServer


