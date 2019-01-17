#!/usr/bin/env bash

#!/bin/bash

IFS=' ' read -ra BUFFER <<< "$1"

function check_for_memory()
{
	/usr/local/nagios/libexec/check_tomcat -n tomcat -k memory -w 60 -c 65;
}

function check_for_threads()
{
	/usr/local/nagios/libexec/check_tomcat -n tomcat -k threads -w 20 -c 10;
}

function check_for_sessions()
{
	/usr/local/nagios/libexec/check_tomcat -n tomcat -k sessions -w 25 -c 15;
}

function check_for_gc()
{
        /usr/local/nagios/libexec/check_tomcat -n tomcat -k gc -w 75 -c 90;
}

function check_for_requestthroughput()
{
        /usr/local/nagios/libexec/check_tomcat -n tomcat -k requestthroughput -w 75 -c 90;
}
    
function check_for_responsetime()
{
        /usr/local/nagios/libexec/check_tomcat -n tomcat -k responsetime -w 75 -c 90;
}

function check_for_pools()
{
        /usr/local/nagios/libexec/check_tomcat -n tomcat -k pools -w 75 -c 90;
}

function check_for_uptime()
{
        /usr/local/nagios/libexec/check_tomcat -n tomcat -k uptime -w 10 -c 24;
}

function is_memory_ok()
{
	[[ "$(check_for_memory)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_threads_ok()
{
	[[ "$(check_for_threads)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_sessions_ok()
{
	[[ "$(check_for_sessions)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_gc_ok()
{
        [[ "$(check_for_gc)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_requestthroughput_ok()
{
        [[ "$(check_for_requestthroughput)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_responsetime_ok()
{
        [[ "$(check_for_responsetime)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}

function is_uptime_ok()
{
        [[ "$(check_for_uptime)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
}
function is_pools_ok()
{
        [[ "$(check_for_pools)" =~ ^(CRITICAL) ]] && echo "1" || echo "0"
} 

function init_error_code()
{
        ERROR_CODE=()
}

function set_error_code()
{
	ERROR_CODE+=("$1")
}
function get_error_code()
{
echo "${ERROR_CODE[@]}"
}

function set_final_list()
{
F_ERROR=()
for i in "${ERROR_CODE[@]}"; do
    skip=
    for j in "${BUFFER[@]}"; do
        [[ $i == $j ]] && { skip=1; break; }
    done
    [[ -n $skip ]] || F_ERROR+=("$i")
done
}

function get_final_list()
{
echo "${F_ERROR[@]}"
}

init_error_code
if [[ "$(is_memory_ok)" -eq "1" ]]
then 
    set_error_code MEMORY
fi
if [[ "$(is_threads_ok)" -eq "1" ]]
then
    set_error_code THREAD 
fi
if [[ "$(is_pools_ok)" -eq "1" ]]
then
    set_error_code POOL
fi
if [[ "$(is_uptime_ok)" -eq "1" ]]
then
    set_error_code UPTIME 
fi
if [[ "$(is_requestthroughput_ok)" -eq "1" ]]
then
    set_error_code REQUESTTHROUGHPUT
fi
if [[ "$(is_responsetime_ok)" -eq "1" ]]
then
    set_error_code RESPONSETIME
fi
if [[ "$(is_gc_ok)" -eq "1" ]]
then
    set_error_code GC
fi
if [[ "$(is_sessions_ok)" -eq "1" ]]
then
    set_error_code SESSION
fi 

set_final_list

echo "$(get_final_list)"  

