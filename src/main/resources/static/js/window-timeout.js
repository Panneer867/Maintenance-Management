
if (window.location.pathname + window.location.search === '/login?logout') {
	setTimeout(function() { window.location = "/login"; }, 2000);
}
if (window.location.pathname + window.location.search === '/login?success') {
	setTimeout(function() { window.location = "/login"; }, 2000);
}

if (window.location.pathname + window.location.search === '/login?error') {
	setTimeout(function() { window.location = "/login"; }, 2000);
}
