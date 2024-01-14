@import org.example.hexlet.util.NamedRoutes

@template.layout.index(content = @`
    <form action="${NamedRoutes.SESSIONS}" method="post">
        <label>
            <input type="text" placeholder="Nickname" name="nickname">
        </label>
        <label>
            <input type="password" placeholder="Password" name="password">
        </label>
        <input type="submit">
    </form>
`)