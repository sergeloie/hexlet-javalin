@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.index(content = @`
    <body>
    <main>
        <h1>Привет, Хекслет!</h1>
        @if(!page.getVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    </main>
    </body>
`)