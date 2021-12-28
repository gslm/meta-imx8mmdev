
# Useful commands
Assuming default workspace folder (not the meta-plant layer root dir)
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

### 7. Using devtool to modify kernel configuration (under test)
```
devtool modify linux-imx
devtool status
devtool menuconfig linux-imx
bitbake linux-imx
devtool deploy-target linux-imx root@192.168.0.108
bitbake -c savedefconfig linux-imx
```

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

## References
https://wiki.st.com/stm32mpu/wiki/BitBake_cheat_sheet





