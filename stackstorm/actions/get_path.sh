#!/usr/bin/env bash
#!/bin/bash

function set_instance_base()
{
        INSTANCE_BASE="$(get_instance_proc | sed -r 's/^.*catalina\.base=([^ ]+).*$/\1/' | sort | uniq )"
}
function set_instance_proc()
{
        INSTANCE_PROC="$(ps -C java -o cmd | grep "\/tomcat\ ")"
}
function get_instance_proc()
{
        echo "${INSTANCE_PROC}"
}
function get_instance_base()
{
        echo "${INSTANCE_BASE}"
}
function fetch_path()
{
set_instance_proc
set_instance_base
}

fetch_path
echo "$(get_instance_base)"
