@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<%--<html lang="en">--%>
<%--    <head>--%>
<%--        <title>Hexlet</title>--%>
<%--    </head>--%>
<%--        <body>--%>
<%--    <h1>${page.getHeader()}</h1>--%>
<%--    @if(page.getCourses().isEmpty())--%>
<%--        <p>No any courses</p>--%>
<%--    @else--%>
<%--        @for(var course : page.getCourses())--%>
<%--            <div>--%>
<%--                <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>--%>
<%--                <p>${course.getDescription()}</p>--%>
<%--            </div>--%>
<%--        @endfor--%>
<%--    @endif--%>
<%--    </body>--%>
<%--</html>--%>

@template.layout.index(content = @`
    <form action="/courses" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Искать">
    </form>
    @if(page.getCourses().isEmpty())
        <p>No any courses</p>
    @else
        @for(var course : page.getCourses())
            <div>
                <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
                <p>${course.getDescription()}</p>
            </div>
        @endfor
    @endif
`)
