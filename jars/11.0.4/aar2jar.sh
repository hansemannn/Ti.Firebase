#!/bin/sh
 
if [ $# -lt 1 ]; then
  echo "Usage: $0 <aar_file> [<output_path>]" 1>&2
  exit 1
fi
 
unzip $1 -d /tmp/aar2jar > /dev/null
if [ -z "$2" ]; then
  dir=.
else
  dir=$2
fi
mv /tmp/aar2jar/classes.jar $dir/`basename $1 .aar`.jar
rm -rf /tmp/aar2jar