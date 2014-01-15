Use Install VMWare Tools option in VMWare Client to attach media

`````````````shell`````````````````
#Update the server

sudo apt-get -y update
sudo apt-get -y upgrade

#Create the mount point
sudo mkdir -p /media/cdrom

#Mount the ISO
sudo mount /dev/cdrom /media/cdrom
#Change Directory

cd /media/cdrom

#Copy the tar file to your /tmp directory
sudo cp VM*.tar.gz /tmp

#Install Build tools if necessary
sudo apt-get -y install linux-headers-server build-essential

#Change Directory
cd /tmp

#Unmount the ISO
sudo umount /media/cdrom

#Expand the tar
sudo tar xzvf VM*.tar.gz

#Change Directory
cd vmware-tools-distrib

#To prevent a potential error in the install script on Ubuntu 11.10+, create a special directory
#Unable to create symlink “/usr/lib64/libvmcf.so” pointing to file ”/usr/lib/vmware-tools/lib64/libvmcf.so/libvmcf.so”.
sudo mkdir -p /usr/lib64

#Run the Install Script. The -d flag automatically answers the default to all questions. To customize it, just omit the -d.
sudo ./vmware-install.pl -d

#Reboot
sudo reboot
````````````````````````````````````

gcc path issue:
``````
sudo apt-get install aptitude
sudo aptitude install libglib2.0-0
sudo aptitude install gcc-4.7 make linux-headers-`uname -r` -y

sudo apt-get install build-essential
sudo apt-get install gcc-4.7 linux-headers-`uname -r`
``````

kernel headers path issue:
cd /lib/modules/$(uname -r)/build/include/linux
``````
sudo ln -s ../generated/utsrelease.h
sudo ln -s ../generated/autoconf.h
sudo ln -s ../generated/uapi/linux/version.h 
``````
and then set the path to /usr/src/linux-headers-$(uname -r)/include