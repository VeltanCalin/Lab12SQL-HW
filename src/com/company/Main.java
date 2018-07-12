
CREATE TABLE public.users
        (
        iduser bigint NOT NULL DEFAULT nextval('users_iduser_seq'::regclass),
        username character(30),
        parola character(35),
        age bigint,
        city character(25),
        blocked boolean,
        CONSTRAINT pkusers PRIMARY KEY (iduser)
        )


        CREATE TABLE public.posts
        (
        idpost bigint NOT NULL DEFAULT nextval('posts_idpost_seq'::regclass),
        message character(1000),
        postdate date,
        iduser bigint,
        CONSTRAINT pkpost PRIMARY KEY (idpost),
        CONSTRAINT fkuser FOREIGN KEY (iduser)
        REFERENCES public.users (iduser) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
        )

       2. Sa se afiseze toate postarile unui user
        select message,postdate
        from posts
        where posts.iduser=1;


       3. afisare useri

        select username
        from users

        4. Sa se afiseze toti userii care nu sunt blocati
        select username
        from users
        where blocked=false

        5. Sa se afiseze userul cu varsta cea mai mica
        select username,age
        from users
        where age = (select min(age) from users);


        6. Sa se afiseze userii cu varsta intre 23 si 33 de ani , ordonati dupa varsta
        select username,age
        from users
        where age>23 and age<33
        order by age asc

        7. sa se afizeze media varstei userilor blocati
        select avg(age) as average
        from users
        where blocked=TRUE

        8.  sa se afiseze userii neblocati din tokyo
        select username
        from users
        where blocked=true and city='tokyo'

        9. sa se afiseze postarile userilor neblocati din bucuresti care au varsta peste 30 de ani
        select posts.message
        from posts
        inner join users
        on posts.iduser=users.iduser
        where users.city = 'bucuresti' and users.blocked = TRUE and users.age > 30


        10. sa sa afiseze userul cu cele mai multe postari
        select username,count(message) as "numar mesaje"
        from users
        join posts
        on users.iduser=posts.iduser
        group by username
        order by count(message)desc
        limit 2

        11. sa se afiseze postarile userilor care incep cu numele D si sunt postate intre 1 si 31 martie 2016
        select message
        from posts
        join users
        on posts.iduser=users.iduser
        where username like 'd%' and (posts.postdate >'20160301' and posts.postdate<'20160331');


        12. sa se afiseze postarile ordonate dupa data postarii descendent, indiferent de user
        select message,postdate
        from posts
        order by postdate desc;

        13. sa se stearga postarile userilor sub 18 ani care contin  textul ‘ee’
        delete from posts
        using users
        where posts.iduser=users.iduser
        and message like'%ee%' and age<30


