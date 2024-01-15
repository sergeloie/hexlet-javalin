@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.index(
page = page,
content = @`
    <form action="${NamedRoutes.COURSES}" method="get">
        <label>
            <input type="search" required name="term" value="${page.getTerm()}">
        </label>
        <input type="submit" value="Искать">
    </form>
    @if(page.getCourses().isEmpty())
        <p>No courses</p>
    @else
        @for(var course : page.getCourses())
            <div>
                <h2><a href="${NamedRoutes.courseShow(course.getId())}">${course.getName()}</a></h2>
                <p>${course.getDescription()}</p>
            </div>
        @endfor
    @endif
`)
