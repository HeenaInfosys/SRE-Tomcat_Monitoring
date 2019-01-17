#!/usr/bin/env bash

#!/bin/bash
IFS=' ' read -ra ERROR <<< "$1"
IFS=' ' read -ra BUFFER <<< "$2"
IFS=' ' read -ra INSTANCE_BASE <<< "$3"

function get_instance_base()
{
echo "${INSTANCE_BASE}"
}

function memory_solution()
{   

cd $(get_instance_base)
sudo chmod -R 777 webapps temp logs work conf
sudo $(get_instance_base)/bin/catalina.sh stop
sudo echo -e 'export CATALINA_OPTS="$CATALINA_OPTS -Xms512m"\nexport CATALINA_OPTS="$CATALINA_OPTS -Xmx8192m"\nexport CATALINA_OPTS="$CATALINA_OPTS
 -XX:MaxPermSize=256m -XX:+UseConcMarkSweepGC -XX:+CMSPermGenSweepingEnabled -XX:+CMSClassUnloadingEnabled"'\
| sudo tee "$(get_instance_base)"'/bin/setenv.sh' > '/dev/null'
sudo systemctl daemon-reload
sudo $(get_instance_base)/bin/catalina.sh start
sudo systemctl restart tomcat
sudo $(get_instance_base)/bin/catalina.sh stop
sudo $(get_instance_base)/bin/catalina.sh start
}

function parse_data()
{
xmlstarlet sel -t -v ${1} ${2}
}

function thread_solution()
{
cd $(get_instance_base)
sudo chmod -R 777 webapps temp logs work conf
sudo $(get_instance_base)/bin/catalina.sh stop
MAX_THREADS=$(parse_data "/Server/Service/Connector[@protocol='HTTP/1.1']/@maxThreads" "$(get_instance_base)/conf/server.xml")
xmlstarlet ed --inplace -u "/Server/Service/Connector[@protocol='HTTP/1.1']/@maxThreads" -v "$(( $MAX_THREADS+50 ))" "$(get_instance_base)/conf/server.xml"
sudo systemctl daemon-reload
sudo $(get_instance_base)/bin/catalina.sh start
sudo systemctl restart tomcat       
}

function app_solution()
{
echo "app_solution"
}

function session_solution()
{

cd $(get_instance_base)
sudo chmod -R 777 webapps temp logs work conf
MAX_SESSIONS=$(parse_data "/Context/Manager/@maxActiveSessions" "$(get_instance_base)/conf/context.xml")
xmlstarlet ed --inplace -u "/Context/Manager/@maxActiveSessions" -v "$(( $MAX_SESSIONS+50 ))" "$(get_instance_base)/conf/context.xml"

}
function pool_solution()
{
echo "pool_solution"
}
function requestthroughput_solution()
{
 echo "requestthroughput"
}
function responsetime_solution()
{
echo "responsetime"
}

function uptime_solution()
{
sudo systemctl daemon-reload
sudo systemctl restart tomcat
}

function gc_solution()
{
memory_solution
}



function get_buffer_list()
{
echo "${BUFFER[@]}"
}

if [[ -z ${ERROR[@]} ]]
then
   BUFFER=()   
elif [[ "${ERROR[@]}" = *"MEMORY"* ]]
then 
BUFFER+=('MEMORY')   
memory_solution >/dev/null
  
elif [[ "${ERROR[@]}" = *"GC"* ]]
then
BUFFER+=('GC') 
memory_solution >/dev/null
elif [[ "${ERROR[@]}" = *"THREAD"* ]]
then
BUFFER+=('THREAD')
  thread_solution >/dev/null
elif [[ "${ERROR[@]}" = *"POOL"* ]]
then
  pool_solution >/dev/null
   BUFFER+=('POOL')
elif [[ "${ERROR[@]}" = *"SESSION"* ]]
then
   session_solution >/dev/null
   BUFFER+=('SESSION')
elif [[ "${ERROR[@]}" = *"REQUESTTHROUGHPUT"* ]]
then
   requestthroughput_solution >/dev/null
   BUFFER+=('REQUESTTHROUGHPUT')
elif [[ "${ERROR[@]}" = *"RESPONSETIME"* ]]
then
   responsetime_solution >/dev/null
   BUFFER+=('RESPONSETIME')
elif [[ "${ERROR[@]}" = *"UPTIME"* ]]
then
   uptime_solution >/dev/null
   BUFFER+=('UPTIME')
fi

echo $(get_buffer_list)
