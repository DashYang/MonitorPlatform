#!bin/sh
#initialise the running environment
if [ -n "`/etc/init.d/glusterd status|grep "stopped"`" ];then
	/etc/init.d/glusterd start
fi

#sh reset_vol

#sh reset_node

echo "initialise done!"
