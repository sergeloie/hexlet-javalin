@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
content = @`
    <a href="/users/build">New User</a>
    @for(var course : page.getCourses())
        <div>${course.getId()}</div>
    @endfor
`
)
