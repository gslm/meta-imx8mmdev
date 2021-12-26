# An image is a top-level recipe. It has a description, license and inherits the core-image class.
DESCRIPTION = "Example image of meta-plant experimental layer."

# Used to save space (run command du-sh tmb/ from build folder before/after to check difference)
INHERIT += "rm_work"

# No need for splash here
IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image


