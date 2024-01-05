@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.index(content = @`
    @for (var user : page.getUsers())
        <p>${user.toString()}</p>
    @endfor
`)
