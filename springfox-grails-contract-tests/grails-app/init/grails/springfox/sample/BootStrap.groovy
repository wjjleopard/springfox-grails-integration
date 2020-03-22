package grails.springfox.sample

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper

@Slf4j
class BootStrap {

    @Autowired
    DocumentationPluginsBootstrapper swaggerBootstrapper

    def init = { servletContext ->
        ['Rock', 'Pop', 'Metal', 'Folk'].each {
            new Genre(name: it).save()
        }
        Book book = new Book(name: "book name").save()
        Label label = new Label(
            name: "label name",
            address: "label address"
        ).save()
        Artist artist = new Artist(
            name: "wjj",
            isBand: false,
            signedTo: label
        ).save()
        Genre genre = new Genre(name: "genre name").save()

        Album album = new Album(
            title: "album title",
            subtitle: "sub title",
            coverImage: "http://a.com/1.jpb",
            albumArtist: artist,
            releaseDate: new Date(),
            rating: Rating.FIVE_STAR,
            label: label,
            genre: genre,
            ).save()

        Song song = new Song(
            title: "song title",
            artist: artist,
            genre: genre,
            album: album
        ).save()

        log.info("init! book: {}", book)
        log.info("init! label: {}", label)
        log.info("init! artist: {}", artist)
        log.info("init! genre: {}", genre)
        log.info("init! song: {}", song)
        log.info("init! album: {}", album)
        swaggerBootstrapper.start()
    }
    def destroy = {
    }
}
