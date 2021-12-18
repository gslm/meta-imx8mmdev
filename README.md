
# Useful commands
Assuming default workspace folder (not the meta-plant layer root dir)
<br/><br/>

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
devtool build linux-imx

devtool menuconfig linux-imx
bitbake -c savedefconfig linux-imx
```

### Example image locations
* ./sources/poky/meta/recipes-extended/images/core-image-full-cmdline.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-core.bb
* ./sources/meta-imx/meta-sdk/recipes-fsl/images/imx-image-multimedia.bb
```
find . -name '*imx-image-core*'
```

### Listing packagegroups
```
find . -type f -name "*.bb" | grep packagegroup
find . -type f -name "*.bb" | grep packagegroup | grep imx
```

### References
https://wiki.st.com/stm32mpu/wiki/BitBake_cheat_sheet





