@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

@template.layout.index(content = @`
    <div>${page.getCourse().toString()}</div>
`)
