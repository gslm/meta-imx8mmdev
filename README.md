
# Useful commands
Assuming default workspace folder (not the meta-plant layer root dir)
<br/><br/>



### Show current layers
```
bitbake-layers show-layers
```

### Open layer config file & local configs
```
code build-wayland/conf/bblayers.conf
code build-wayland/conf/local.conf
```

### Determine where a recipe is located (Example: kernel recipe)
```
find . -name 'linux-imx*bb'
```

### Using bitbake to check which packages are included in build
```
bitbake -g core-image-full-cmdline && cat pn-buildlist | grep -ve "native" | sort | uniq
```

### Open image manifest of default build
```
code build-wayland/tmp/deploy/images/imx8mmevk/core-image-full-cmdline-imx8mmevk.manifest
```

### Determine where the kernel source is hosted
```
rep url build-wayland/tmp/work/imx8mmevk-poky-linux/linux-imx/5.10.72+gitAUTOINC+a68e31b63f-r0/git/.git/config
```

### Installing uuu & Flashing to target
```
sudo snap install universal-update-utility

sudo uuu -b emmc_all build-wayland/tmp/deploy/images/imx8mmevk/imx-boot-imx8mmevk-sd.bin-flash_evk build-wayland/tmp/deploy/images/imx8mmevk/core-image-full-cmdline-imx8mmevk.wic.bz2/*
```

### Using devtool to modify kernel configuration (example)
```
devtool modify linux-imx
devtool status
devtool menuconfig linux-imx
bitbake linux-imx

devtool deploy-target linux-imx root@192.168.0.108

bitbake -c savedefconfig linux-imx
```

### Example image locations

##### command to check images (from yocto base folder)
* ls sources/meta*/recipes*/images/*.bb

##### default image (in layer meta-plant)
* ./sources/meta-plant/recipes-core/images/imx-image-plant.bb

##### other common images
* ./sources/poky/meta/recipes-extended/images/core-image-full-cmdline.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-core.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-multimedia.bb
```
find . -name '*imx-image-core*'
find . -name '*imx-image-plant.bb*'
```


### Rootfs location & size
du -sh build-wayland/tmp/work/imx8mmevk-poky-linux/imx-image-plant/1.0-r0/rootfs/


### Device tree files

##### kernel
```
code build-wayland/tmp/work-shared/imx8mmevk/kernel-source/arch/arm64/boot/dts/freescale/imx8mm-evk.dts
code build-wayland/workspace/sources/linux-imx/arch/arm64/boot/dts/freescale/imx8mm-evk.dts (after running devtool)
```

##### u-boot
```
code build-wayland/tmp/work/imx8mmevk-poky-linux/u-boot-imx/2021.04-r0/git/arch/arm/dts/imx8mm-evk.dts
code build-wayland/tmp/work/imx8mmevk-poky-linux/u-boot-imx/2021.04-r0/git/arch/arm/dts/imx8mm-evk.dtsi
```

### Listing packagegroups
```
find . -type f -name "*.bb" | grep packagegroup
find . -type f -name "*.bb" | grep packagegroup | grep imx
```

### References
https://wiki.st.com/stm32mpu/wiki/BitBake_cheat_sheet





