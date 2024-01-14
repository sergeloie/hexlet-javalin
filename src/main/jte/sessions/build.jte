@import org.example.hexlet.util.NamedRoutes

@template.layout.index(content = @`
    <form action="${NamedRoutes.SESSIONS}" method="post">
        <input type="text" placeholder="Nickname" name="nickname">
        <input type="password" placeholder="Password" name="password">
        <input type="submit">
    </form>
`)