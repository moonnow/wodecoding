/*==============================================================*/
/* Name:<数据库表> Table: PRO_DT                                 */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow huaxinwodecs PRO_DT > PRO_DT.sql
*/
drop table if exists PRO_DT;
create table PRO_DT (
  DT_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '数据库表编号',
  DT_SQL                          text                character set utf8 collate utf8_bin  not null comment '数据库表sql',
  DT_NAME                         varchar(64)         character set utf8 collate utf8_bin      null comment '表名',
  DT_NAME_ANNOTATION              varchar(64)         character set utf8 collate utf8_bin      null comment '表名注释',
  DT_PREFIX                       varchar(64)         character set utf8 collate utf8_bin      null comment '数据库表前缀',
  INITIAL_CASE_ENTITY_NAME        varchar(64)         character set utf8 collate utf8_bin      null comment '首字母大写实体类名',
  INITIAL_LOWERCASE_ENTITY_NAME   varchar(64)         character set utf8 collate utf8_bin      null comment '首字母小写实体类名',
  PRO_PATH                        varchar(1024)       character set utf8 collate utf8_bin      null comment '项目路径',
  PRO_ALL_NAME                    varchar(255)        character set utf8 collate utf8_bin      null comment '项目全称',
  PRO_NAME                        varchar(255)        character set utf8 collate utf8_bin      null comment '项目名称',
  primary key (DT_ID),
  unique key (DT_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '数据库表';

/*==============================================================*/
/* Name:<列> Table: PRO_COLUMNS                                 */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow huaxinwodecs PRO_COLUMNS > PRO_COLUMNS.sql
*/
drop table if exists PRO_COLUMNS;
create table PRO_COLUMNS (
  COLUMNS_ID                      char(36)            character set utf8 collate utf8_bin  not null comment '列编号',
  COLUMN_NAME                     varchar(64)         character set utf8 collate utf8_bin      null comment '列名',
  COLUMN_NAME_ANNOTATION          varchar(64)         character set utf8 collate utf8_bin      null comment '列名注释',
  DATA_TYPE                       varchar(64)         character set utf8 collate utf8_bin      null comment '数据类型',
  IS_NULL                         char(1)             character set utf8 collate utf8_bin      null comment '是否为空',
  INITIAL_CASE_COLUMN_NAME        varchar(64)         character set utf8 collate utf8_bin      null comment '首字母大写列名',
  INITIAL_LOWERCASE_COLUMN_NAME   varchar(64)         character set utf8 collate utf8_bin      null comment '首字母小写列名',
  WEIGHT_ORDER                    integer                                                      null comment '排序权重',

  DT_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '数据库表编号',
  primary key (COLUMNS_ID),
  unique key (COLUMN_NAME, DT_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '列';
alter table PRO_COLUMNS add constraint FK_PRO_COLUMN_DT_ID foreign key (DT_ID) references PRO_DT(DT_ID);

/*==============================================================*/
/* Name:<主键> Table: PRO_PK                                    */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow huaxinwodecs PRO_PK > PRO_PK.sql
*/
drop table if exists PRO_PK;
create table PRO_PK (
  PK_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '主键编号',
  DT_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '数据库表编号',
  COLUMNS_ID                      char(36)            character set utf8 collate utf8_bin  not null comment '列编号',
  primary key (PK_ID),
  unique key (DT_ID, COLUMNS_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '主键';
alter table PRO_PK add constraint FK_PRO_PK_DT_ID foreign key (DT_ID) references PRO_DT(DT_ID);
alter table PRO_PK add constraint FK_PRO_PK_COLUMNS_ID foreign key (COLUMNS_ID) references PRO_COLUMNS(COLUMNS_ID);

/*==============================================================*/
/* Name:<排序> Table: PRO_SORT                                  */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow huaxinwodecs PRO_SORT > PRO_SORT.sql
*/
drop table if exists PRO_SORT;
create table PRO_SORT (
  SORT_ID                         char(36)            character set utf8 collate utf8_bin  not null comment '排序编号',
  SORT_RULE                       varchar(24)         character set utf8 collate utf8_bin  not null comment '排序规则',
  DT_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '数据库表编号',
  COLUMNS_ID                      char(36)            character set utf8 collate utf8_bin  not null comment '列编号',
  primary key (SORT_ID),
  unique key (DT_ID, COLUMNS_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '排序';
alter table PRO_SORT add constraint FK_PRO_SORT_DT_ID foreign key (DT_ID) references PRO_DT(DT_ID);
alter table PRO_SORT add constraint FK_PRO_SORT_COLUMNS_ID foreign key (COLUMNS_ID) references PRO_COLUMNS(COLUMNS_ID);

/*==============================================================*/
/* Name:<查询> Table: PRO_QUERY                                 */
/*==============================================================*/
/*
mysqldump -hlocalhost -P3306 -uroot -pmoonnow huaxinwodecs PRO_QUERY > PRO_QUERY.sql
*/
drop table if exists PRO_QUERY;
create table PRO_QUERY (
  QUERY_ID                        char(36)            character set utf8 collate utf8_bin  not null comment '查询编号',
  QUERY_TYPE                      varchar(24)         character set utf8 collate utf8_bin  not null comment '查询类型',
  DT_ID                           char(36)            character set utf8 collate utf8_bin  not null comment '数据库表编号',
  COLUMNS_ID                      char(36)            character set utf8 collate utf8_bin  not null comment '列编号',
  WEIGHT_ORDER                    integer                                                      null comment '排序权重',
  primary key (QUERY_ID),
  unique key (QUERY_TYPE, DT_ID, COLUMNS_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '查询';
alter table PRO_QUERY add constraint FK_PRO_QUERY_DT_ID foreign key (DT_ID) references PRO_DT(DT_ID);
alter table PRO_QUERY add constraint FK_PRO_QUERY_COLUMNS_ID foreign key (COLUMNS_ID) references PRO_COLUMNS(COLUMNS_ID);

