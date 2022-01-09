#include <stdio.h>
#include <linux/i2c-dev.h>

int main(int argc, char *argv[]) {
    printf("---Booting from userland--\n");
    return 0;
}
/*
void main() {
	struct gpiod_chip *chip;
	struct gpiod_line *line;
	int req, value;

	chip = gpiod_chip_open("/dev/gpiochip0");
	if (!chip)
		return -1;

	line = gpiod_chip_get_line(chip, 3);
	if (!line) {
		gpiod_chip_close(chip);
		return -1;
	}

	req = gpiod_line_request_input(line, "gpio_state");
	if (req) {
		gpiod_chip_close(chip);
		return -1;
	}

	value = gpiod_line_get_value(line);

	printf("GPIO value is: %d\n", value);

	gpiod_chip_close(chip);
}
*/