/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     31/07/2018 22:19:03                          */
/*==============================================================*/


drop index APORTA_CON_FK;

drop index OA_VAR_FK;

drop index OBJ_APOYO_PK;

drop table OBJ_APOYO;

drop index APORTA_CON2_FK;

drop index RELATIONSHIP_1_FK;

drop index OBJ_ESTRATEGICO_PK;

drop table OBJ_ESTRATEGICO;

drop index OA_VAR2_FK;

drop index RELATIONSHIP_2_FK;

drop index VARIACION_PK;

drop table VARIACION;

/*==============================================================*/
/* Table: OBJ_APOYO                                             */
/*==============================================================*/
create table OBJ_APOYO (
   OBJETIVOAPOYO        VARCHAR(250)         null,
   KPI_OA               VARCHAR(250)         null,
   DEFINICION_OA        VARCHAR(250)         null,
   FRECUENCIA_OA        VARCHAR(250)         null,
   FUENTE_OA            VARCHAR(250)         null,
   NIVELES_OA           VARCHAR(250)         null,
   META_OA              VARCHAR(250)         null,
   RESPONSABLE_OA       VARCHAR(250)         null,
   TENDENCIA_OA         VARCHAR(100)         null,
   INICIATIVA_ESTRATEGICA_OA VARCHAR(250)         null,
   ID_OA                INT4                 not null,
   ID_OE                INT4                 null,
   ID_VAR               INT4                 null,
   AREA_OA              VARCHAR(250)         null,
   APORTA_CON           VARCHAR(250)         null,
   constraint PK_OBJ_APOYO primary key (ID_OA)
);

/*==============================================================*/
/* Index: OBJ_APOYO_PK                                          */
/*==============================================================*/
create unique index OBJ_APOYO_PK on OBJ_APOYO (
ID_OA
);

/*==============================================================*/
/* Index: OA_VAR_FK                                             */
/*==============================================================*/
create  index OA_VAR_FK on OBJ_APOYO (
ID_VAR
);

/*==============================================================*/
/* Index: APORTA_CON_FK                                         */
/*==============================================================*/
create  index APORTA_CON_FK on OBJ_APOYO (
ID_OE
);

/*==============================================================*/
/* Table: OBJ_ESTRATEGICO                                       */
/*==============================================================*/
create table OBJ_ESTRATEGICO (
   OBJETIVO_ESTRATEGICO VARCHAR(250)         null,
   KPI_OA               VARCHAR(250)         null,
   DEFINICION_OA        VARCHAR(250)         null,
   FRECUENCIA_OA        VARCHAR(250)         null,
   FUENTE_OA            VARCHAR(250)         null,
   NIVELES_OA           VARCHAR(250)         null,
   META_OA              VARCHAR(250)         null,
   RESPONSABLE_OA       VARCHAR(250)         null,
   TENDECIA             VARCHAR(100)         null,
   INICIATIVA_ESTRATEGICA_OA VARCHAR(250)         null,
   ID_OE                INT4                 not null,
   ID_OA                INT4                 null,
   ID_VAR               INT4                 null,
   CAPA_AE              VARCHAR(50)          null,
   constraint PK_OBJ_ESTRATEGICO primary key (ID_OE)
);

/*==============================================================*/
/* Index: OBJ_ESTRATEGICO_PK                                    */
/*==============================================================*/
create unique index OBJ_ESTRATEGICO_PK on OBJ_ESTRATEGICO (
ID_OE
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on OBJ_ESTRATEGICO (
ID_VAR
);

/*==============================================================*/
/* Index: APORTA_CON2_FK                                        */
/*==============================================================*/
create  index APORTA_CON2_FK on OBJ_ESTRATEGICO (
ID_OA
);

/*==============================================================*/
/* Table: VARIACION                                             */
/*==============================================================*/
create table VARIACION (
   ID_VAR               INT4                 not null,
   ID_OE                INT4                 null,
   ID_OA                INT4                 null,
   INICIO_ROJO          DECIMAL(10,2)        null,
   FIN_ROJO             DECIMAL(10,2)        null,
   INICIO_AMARILLO      DECIMAL(10,2)        null,
   FIN_AMARILLO         DECIMAL(10,2)        null,
   INICIO_VERDE         DECIMAL(10,2)        null,
   FIN_VERDE            DECIMAL(10,2)        null,
   constraint PK_VARIACION primary key (ID_VAR)
);

/*==============================================================*/
/* Index: VARIACION_PK                                          */
/*==============================================================*/
create unique index VARIACION_PK on VARIACION (
ID_VAR
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on VARIACION (
ID_OE
);

/*==============================================================*/
/* Index: OA_VAR2_FK                                            */
/*==============================================================*/
create  index OA_VAR2_FK on VARIACION (
ID_OA
);

alter table OBJ_APOYO
   add constraint FK_OBJ_APOY_APORTA_CO_OBJ_ESTR foreign key (ID_OE)
      references OBJ_ESTRATEGICO (ID_OE)
      on delete restrict on update restrict;

alter table OBJ_APOYO
   add constraint FK_OBJ_APOY_OA_VAR_VARIACIO foreign key (ID_VAR)
      references VARIACION (ID_VAR)
      on delete restrict on update restrict;

alter table OBJ_ESTRATEGICO
   add constraint FK_OBJ_ESTR_APORTA_CO_OBJ_APOY foreign key (ID_OA)
      references OBJ_APOYO (ID_OA)
      on delete restrict on update restrict;

alter table OBJ_ESTRATEGICO
   add constraint FK_OBJ_ESTR_RELATIONS_VARIACIO foreign key (ID_VAR)
      references VARIACION (ID_VAR)
      on delete restrict on update restrict;

alter table VARIACION
   add constraint FK_VARIACIO_OA_VAR2_OBJ_APOY foreign key (ID_OA)
      references OBJ_APOYO (ID_OA)
      on delete restrict on update restrict;

alter table VARIACION
   add constraint FK_VARIACIO_RELATIONS_OBJ_ESTR foreign key (ID_OE)
      references OBJ_ESTRATEGICO (ID_OE)
      on delete restrict on update restrict;

