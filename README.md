
# Useful commands
<br/><br/>

### 1. Show current layers
```
bitbake-layers show-layers
```
### 2. Open layer config file & local configs
```
code build-wayland/conf/bblayers.conf
code build-wayland/conf/local.conf
```
### 3. Determine where a recipe is located (Example: kernel recipe)
```
find . -name 'linux-imx*bb'
```
### 4 .Using bitbake to check which packages are included in build
```
bitbake -g imx-image-plant && cat pn-buildlist | grep -ve "native" | sort | uniq
```
### 5. Open image manifest of default build
```
code build-wayland/tmp/deploy/images/imx8mmevk/imx-image-plant-imx8mmevk.manifest
```
### 6. Installing uuu & Flashing to target
```
sudo snap install universal-update-utility

sudo uuu -b emmc_all build-wayland/tmp/deploy/images/imx8mmevk/imx-boot-imx8mmevk-sd.bin-flash_evk build-wayland/tmp/deploy/images/imx8mmevk/imx-image-plant-imx8mmevk.wic.bz2/*
```
### 7. Using devtool with menuconfig to modify kernel configuration

##### 7.1 Modifying kernel with menuconfig before deploying
```
devtool modify linux-imx
devtool status (check if linux-imx recipe was correctly added)
devtool menuconfig linux-imx (change configs as needed and save)
devtool finish linux-imx ../sources/meta-plant (kernel *.cfg fragment saved to custom layer)

```
##### 7.2 Deploying python3-pip and nano to a live target with SSH

Best approach so far is to add python3-pip to custom image imx-image-plant and rebuild whole image.
Then, the devtool-target
```
bitbake imx-image-plant -c cleanall && bitbake imx-image-plant
devtool modify python3-pip
devtool deploy-target python3-pip root@192.168.0.115
devtool reset python3-pip
```

test pip3 in target: pip3 install pyserial

```
bitbake imx-image-plant -c cleanall && bitbake imx-image-plant
devtool modify nano
devtool deploy-target nano root@192.168.0.115
devtool reset nano
```
A better solution would be to use devtool add python3-pip ../sources/poky/meta/recipes-devtools/python/python3-pip (working on findind out build errors)


### 8. Example image locations

##### 8.1 command to check images (from yocto base folder)
* ls sources/meta*/recipes*/images/*.bb

##### 8.2 default image (in layer meta-plant)
* ./sources/meta-plant/recipes-core/images/imx-image-plant.bb

##### 8.3 other common images
* ./sources/poky/meta/recipes-extended/images/core-image-full-cmdline.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-core.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-multimedia.bb
```
find . -name '*imx-image-core*'
find . -name '*imx-image-plant.bb*'
```

### 9. Check bitbake build environment variable value (example: MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS)
```
bitbake -e imx-image-plant | grep ^MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS
```

### 10. Rootfs location & size
```
du -sh build-wayland/tmp/work/imx8mmevk-poky-linux/imx-image-plant/1.0-r0/rootfs/
```

### 11. Find out rootfs extra space
```
bitbake -e | grep ^IMAGE_ROOTFS_EXTRA_SPACE
```

### 12. Device tree files

##### 12.1 kernel
```
code build-wayland/tmp/work-shared/imx8mmevk/kernel-source/arch/arm64/boot/dts/freescale/imx8mm-evk.dts
code build-wayland/workspace/sources/linux-imx/arch/arm64/boot/dts/freescale/imx8mm-evk.dts (after running devtool)
```

##### 12.2 u-boot
```
code build-wayland/tmp/work-shared/imx8mmevk/kernel-source/arch/arm64/boot/dts/freescale/imx8mm-evk.dts
code build-wayland/tmp/work-shared/imx8mmevk/kernel-source/arch/arm64/boot/dts/freescale/imx8mm-evk.dtsi
```

### 13. Listing packagegroups
```
find . -type f -name "*.bb" | grep packagegroup
find . -type f -name "*.bb" | grep packagegroup | grep imx
```

### 14. Generating extensible SDK
```
bitbake imx-image-plant -c populate_sdk_ext
```
### 15. Programming with VSCode 

##### 15.1 References
```
https://variwiki.com/index.php?title=Yocto_Programming_with_VSCode&release=RELEASE_HARDKNOTT_V1.1_DART-MX8M-PLUS
```
##### 15.2 List of packages in build (example for recipes containing *ssh)
```
oe-pkgdata-util list-pkgs | grep ssh
bitbake-layers show-recipes | grep ssh
```
##### 15.3 oe-pkgdata-util lookup-recipe
```
oe-pkgdata-util lookup-recipe ssh
```

##### 15.4 installing SDK
```
bitbake imx-image-plant -c populate_sdk
./fsl-imx-wayland-glibc-x86_64-imx-image-plant-cortexa53-crypto-imx8mmevk-toolchain-5.10-hardknott.sh (from build-wayland/tmp/deploy/sdk)

. /opt/fsl-imx-wayland/5.10-hardknott/environment-setup-cortexa53-crypto-poky-linux (sourcing from new shell)
export LDFLAGS=
```

##### 15.5 manual build (calls make in plant-hello-world)
```
rm -f hello.bin
/opt/fsl-imx-wayland/5.10-hardknott/sysroots/x86_64-pokysdk-linux/usr/bin/aarch64-poky-linux/aarch64-poky-linux-g++ --sysroot=/opt/fsl-imx-wayland/5.10-hardknott/sysroots/cortexa53-crypto-poky-linux  -Og main.c -g -o hello.bin
``` 
##### 15.6 transfer binary to target with scp (run from build folder; replace IP with current target IP)
```
scp ../sources/meta-plant/plant-hello-world/hello.bin root@192.168.0.102:/home/root/
```

### 16. Locating some driver files in the SDK
```
find . -wholename "*drivers/gpio/gpio-mxc.c*"
```

### 17. Check machines supported in current configuration
```
ls sources/meta-imx/meta-bsp/conf/machine
```


## References
https://wiki.st.com/stm32mpu/wiki/BitBake_cheat_sheet







