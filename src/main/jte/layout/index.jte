@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.util.ResourceRoutes
@import org.example.hexlet.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="${ResourceRoutes.BOOTSTRAP_LINK}" rel="stylesheet" integrity="${ResourceRoutes.BOOTSTRAP_INTEGRITY}" crossorigin="anonymous">
    <link href="${ResourceRoutes.ANIMATED_CSS_LINK}" rel="stylesheet" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<header class="d-flex justify-content-center py-3 animate__animated animate__rubberBand">
    <ul class="nav nav-pills">
        <li class="nav-item"><a href="${NamedRoutes.ROOT}" class="nav-link active" aria-current="page">Root page</a></li>
        <li class="nav-item"><a href="${NamedRoutes.COURSES}" class="nav-link">All Courses</a></li>
        <li class="nav-item"><a href="${NamedRoutes.COURSES_BUILD}" class="nav-link">Build Course</a></li>
        <li class="nav-item"><a href="${NamedRoutes.USERS}" class="nav-link">All Users</a></li>
        <li class="nav-item"><a href="${NamedRoutes.USERS_BUILD}" class="nav-link">Build Users</a></li>
        <li class="nav-item"><a href="${NamedRoutes.SESSIONS}" class="nav-link">Session</a></li>
        <li class="nav-item"><a href="${NamedRoutes.SESSIONS_BUILD}" class="nav-link">Build Session (Login)</a></li>
    </ul>
</header>
<div>
    @if(page !=null && page.getFlash() != null)
        <p>${page.getFlash()}</p>
    @endif
</div>
${content}
<footer class="footer fixed-bottom d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
        <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
            <svg class="bi" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
        </a>
        <span class="text-muted">© 2021 Company, Inc</span>
    </div>
</footer>
</body>
</html>
