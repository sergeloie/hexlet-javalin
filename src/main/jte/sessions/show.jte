@import org.example.hexlet.dto.SessionPage
@param SessionPage page

@template.layout.index(content = @`
    <h1>Session</h1>
    @if(page.getCurrentUser() != null)
        Welcome, ${page.getCurrentUser()}.
        To unlogin delete cookie JSESSIONID from browser
    @endif
`)
