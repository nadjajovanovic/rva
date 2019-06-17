drop table if exists kredit cascade;
drop table if exists klijent cascade;
drop table if exists tip_racuna cascade;
drop table if exists racun cascade;

drop sequence if exists kredit_seq;
drop sequence if exists klijent_seq;
drop sequence if exists tip_racuna_seq;
drop sequence if exists racun_seq;

create table kredit(
	id integer not null,
	naziv varchar(100) not null,
	oznaka varchar(20) not null,
	opis varchar(500) not null
);

create table klijent(
	id integer not null,
	ime varchar(50) not null,
	prezime varchar(50) not null,
	broj_lk integer not null,
	kredit integer not null
);

create table tip_racuna(
	id integer not null,
	naziv varchar(100) not null,
	oznaka varchar(20) not null,
	opis varchar(500) not null
);

create table racun(
	id integer not null,
	naziv varchar(100) not null,
	oznaka varchar(20) not null,
	opis varchar(500) not null,
	tip_racuna integer not null,
	klijent integer not null
);

alter table kredit add constraint PK_kredit primary key (id);
alter table klijent add constraint PK_klijent primary key (id);
alter table tip_racuna add constraint PK_tip_racuna primary key (id);
alter table racun add constraint PK_racun primary key (id);

alter table klijent add constraint FK_klijent_kredit 
foreign key (kredit) references kredit (id);
alter table racun add constraint FK_racun_tip_racuna 
foreign key (tip_racuna) references tip_racuna (id);
alter table racun add constraint FK_racun_klijent 
foreign key (klijent) references klijent (id);

create index IDXFK_klijent_kredit  on klijent (kredit);
create index IDXFK_racun_tip_racuna on racun (tip_racuna);
create index IDXFK_racun_klijent on racun (klijent);

create sequence kredit_seq increment 1;
create sequence klijent_seq increment 1;
create sequence tip_racuna_seq increment 1;
create sequence racun_seq increment 1;