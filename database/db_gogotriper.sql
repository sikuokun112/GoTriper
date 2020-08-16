create database gogotriper;


use gogotriper;



create table account(
	user_id bigint primary key auto_increment,
	username varchar(45) ,
    password nvarchar(128) ,
    fullname nvarchar(128),
    email nvarchar(128),
    enable BIT 
);

alter table account add constraint account_user_UK unique(username);
create table role(
        role_id bigint  primary key auto_increment,
        role_name varchar(45) not null
);
alter table role add constraint role_user_UK unique(role_name);
create table user_role(
    user_id bigint ,
    role_id bigint 
);
alter table user_role add constraint user_role_UK unique(user_id,role_id);
alter table user_role add constraint user_role_FK1 foreign key (user_id) references account(user_id);
alter table user_role add constraint user_role_FK2 foreign key (role_id) references role(role_id);

create table persistent_logins(
	username varchar(64) ,
    series varchar(64) ,
    token varchar(64) ,
    last_used timestamp ,
    primary key (series)
);

insert into account values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',"USER","user@gmail.com", 1);
 
insert into account values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',"ADMIN","admin@gmail.com", 1);
 
---
 
insert into role values (1, 'ROLE_ADMIN');
 
insert into role values (2, 'ROLE_USER');
 
---
 
insert into user_role values (1, 1);
 
insert into user_role values (1, 2);
 
insert into user_role values (2, 2);


create table danhmuc(
	danhmuc_id int not null auto_increment primary key,
    tendanhmuc nvarchar(45)
);
insert into danhmuc values(1,"Cuộc Sống");
insert into danhmuc values(2,"Ẩm Thực");
insert into danhmuc values (3,"Khách Sạn");
insert into danhmuc values (4,"Du Lịch");
insert into danhmuc values (5,"Văn Hoá");


create table baidang(
	baidang_id int not null auto_increment primary key,
    tieude nvarchar(45),
    noidung text,
    thoigiandang timestamp,
    thoigianhethan timestamp,
    soluotxem int,
    danhmuc_id int,
	diadiem_id int,
    user_id bigint
);

create table diadiem(
	diadiem_id int not null auto_increment primary key,
    tendiadiem nvarchar(45),
    giamin int,
    giamax int,
    giohoatdong timestamp,
    giodongcua timestamp,
    kinhdo varchar(45),
    vido varchar(45),
    diachi varchar(255),
    sdt int,
    noidung text,
    user_id bigint,
    tinhthanh_id int
);
create table image(
	image_id int not null auto_increment primary key,
    imageurl nvarchar(255),
    diadiem_id int,
    baidang_id int,
    user_id bigint
);

create table tinhthanh(
	tinhthanh_id int not null auto_increment primary key,
    ten nvarchar(45)
);



create table binhluan(
	binhluan_id int not null auto_increment primary key,
    tieude nvarchar(50),
    noidung text,
    diemdanhgia float,
    thoigian timestamp,
    baidang_id int,
    user_id bigint
);

AlTER table baidang add constraint BD_DM_FK foreign key(danhmuc_id) references danhmuc(danhmuc_id);
AlTER table baidang add constraint BD_diadiem_FK foreign key(diadiem_id) references diadiem(diadiem_id);
ALTER table baidang add constraint BD_USER_FK foreign key(user_id) references account(user_id);

ALTER table diadiem add constraint DD_USER_FK foreign key(user_id) references account(user_id);
ALter table diadiem add constraint dd_tinhthanh_FK foreign key(tinhthanh_id) references tinhthanh(tinhthanh_id);

AlTER table image add constraint image_diadiem_FK foreign key(diadiem_id) references diadiem(diadiem_id);
Alter table image add constraint image_baidang_id foreign key(baidang_id) references baidang(baidang_id);
Alter table image add constraint image_user_FK foreign key(user_id) references account(user_id);


ALTER table binhluan add constraint binhluan_baidang_FK foreign key(baidang_id) references baidang(baidang_id);
AlTER table binhluan add constraint binhluan_taikhoan_FK foreign key(user_id) references account(user_id);



insert into tinhthanh values(1,"Hồ Chí Minh");
insert into tinhthanh values(2,"Hà Nội");
insert into tinhthanh values(3,"Lâm Đồng");
insert into tinhthanh values(4,"Đà Nẵng");
insert into tinhthanh values(5,"Huế");





