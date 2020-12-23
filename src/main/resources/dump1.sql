create table if not exists person
(
    id            bigint auto_increment
    primary key,
    created_date  date         null,
    modified_date date         null,
    cpf           varchar(255) null,
    last_name     varchar(255) null,
    name          varchar(255) null,
    person_type   int          null
    );

create table if not exists professor
(
    pk_professor_person bigint not null
    primary key,
    constraint FKp0cj8154fihja5mu51q45ixok
    foreign key (pk_professor_person) references person (id)
    );

create table if not exists tcc
(
    id            bigint auto_increment
    primary key,
    created_date  date         null,
    modified_date date         null,
    name          varchar(255) null
    );

create table if not exists professor_tcc
(
    professor_pk_professor_person bigint not null,
    tcc                           bigint not null,
    primary key (professor_pk_professor_person, tcc),
    constraint UK_o2uctsocj378xyv7m8p850jpw
    unique (tcc),
    constraint FK4h4sixbf654m0iuhvnnfgicwk
    foreign key (tcc) references tcc (id),
    constraint FK8v96i1bscl0bfvkd2flm47a1f
    foreign key (professor_pk_professor_person) references professor (pk_professor_person)
    );

create table if not exists studant
(
    code              varchar(255) null,
    pk_studant_person bigint       not null
    primary key,
    pk_studant_tcc    bigint       null,
    constraint FK2d5dy6vu1ren4ak1ndqejptkp
    foreign key (pk_studant_tcc) references tcc (id),
    constraint FKcg5t0xdrll9iyowuig3cp6v85
    foreign key (pk_studant_person) references person (id)
    );

create table if not exists tcc_studants
(
    tcc_id   bigint not null,
    studants bigint not null,
    constraint UK_q8qneo7qpqm5msig8rjja84y3
    unique (studants),
    constraint FKnoro5511g4l37y9myrn82w8j9
    foreign key (tcc_id) references tcc (id),
    constraint FKsahud9p7i4oruk0tj13hg3pmy
    foreign key (studants) references studant (pk_studant_person)
    );

