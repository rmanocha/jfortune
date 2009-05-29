#!/bin/bash

iam=`whoami`
if [ $iam != root ];then
		echo "Run this program as root."
		exit 1
fi
cp ./*.jar /usr/share/jfortune
cp jfortune /usr/bin/jfortune
cp ./jstrfile /usr/bin/jstrfile
exit 0
