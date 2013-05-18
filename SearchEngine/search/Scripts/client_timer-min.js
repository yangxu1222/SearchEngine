function tick() {
	var b, e, i, d;
	var c = new Date();
	var g = c.getHours();
	var a = c.getMinutes();
	var f = c.getSeconds();
	if (g < 12) {
		b = g + ":";
		d = "A.M."
	} else {
		if (g == 12) {
			b = "12:";
			d = "P.M."
		} else {
			g = g - 12;
			b = g + ":";
			d = "P.M."
		}
	}
	if (a < 10) {
		e = "0" + a + ":"
	} else {
		e = a + ":"
	}
	if (f < 10) {
		i = "0" + f + " "
	} else {
		i = f + " "
	}
	Clock.innerHTML = b + e + i + d;
	window.setTimeout("tick();", 100)
};