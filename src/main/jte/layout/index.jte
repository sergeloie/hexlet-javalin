@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" rel="stylesheet" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<header class="d-flex justify-content-center py-3 animate__animated animate__rubberBand">
    <ul class="nav nav-pills">
        <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Root page</a></li>
        <li class="nav-item"><a href="/courses" class="nav-link">All Courses</a></li>
    </ul>
</header>
${content}
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
        <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
            <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
        </a>
        <span class="text-muted">© 2021 Company, Inc</span>
    </div>
</footer>
</body>
</html>
