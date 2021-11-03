#!/bin/bash

echo "#------------------------------------------------------------------------------"
echo "# start customer's script compiling..."

############################################
source $android_path/vendor/toptech/scripts/init_var.sh
############################################

#PATH Define#
######################################
######################################

# Product Device Name
#customer_device_name=""
# Product Model Name
#customer_model_name=""

# 368 > 1G: mt5862 512M: mt5862_512m 
build_product="mt5862"

#chip_name
CHIP_NAME=mt5862

#board_name
BOARD_NAME=BD_MT5862-M1V1-N2

#mboot path
#MTK6681P => 151B_optee_MTK_32bit
#mtk9632p => android.MST165B_10AT_19055.rom_emmc.optee.32bit_cn19
#mtk368 => .config.mt5862.mi.M1V1-N2_MT9255_MT5862.rom_emmc.optee.32bit
mboot_bin_path=MT5862-M1V1-N2

#pcb_type
# MTK PCB BOARD
# 1、MTK_PCB_BOARD_MT6681P
# 2、MTK_PCB_BOARD_MT9632P

# Toptech PCB BOARD
# Toptech_PCB_BOARD_P50_368V50
# Toptech_PCB_BOARD_P75_368V65 
# Toptech_PCB_BOARD_P50_368_V30 
# Toptech_PCB_BOARD_M368_V30 
# Toptech_PCB_BOARD_P50_368_TV80 
# Toptech_PCB_BOARD_P50_368_DV50 
# Toptech_PCB_BOARD_P75_368_TV90
# Toptech_PCB_BOARD_EL_MT9255_FA48
# Toptech_PCB_BOARD_EL_MT9255_FA75
pcb_board_type=Toptech_PCB_BOARD_EL_MT9255_FA48

# string, customer short name
cus_id="C001"
# string, product full name
# this value will include into OTA package name
cus_pro_id="LE32T1_EL_MT9255_FA48_TZ2109-164"
# string, software revision
software_revision="V1.0.01"
# string pcb name
pcbname="EL_MT9255_FA48"

#ddr_type 0:512M    1:1G
ddr_type=1

#flash_type
#0:4G EMMC
#1:8G EMMC
use_flash_size_type=1

#tuner and demodulator option
#tuner driver define at Frontend_Enum_Device.h
#The DVBC/DVBT/ISDBTJ83B/ATSC/ATV commonly used: TUNER_R842 TUNER_MXL661 TUNER_ATBM2040
#The DVBS/S2 commonly used: TUNER_AV2017 TUNER_AV2012(same as TUNER_AV2017) TUNER_RDA5815M
tuner=TUNER_R842
s_tuner=TUNER_AV2017

#*** share Xtal options ***
#1. R842_NO_SHARE_XTAL
#2. R842_MASTER_TO_SLAVE_XTAL_IN    //R842+RT710
#3. R842_MASTER_TO_SLAVE_XTAL_OUT
#4. R842_SLAVE_XTAL_OUT             //pure ATV
R842_SHARE_XTAL=1

# DTV System Type
#/* DVB, ATSC and ISDB are mutual exclusion*/
#define MI_SYS_DVBT_ENABLE  (_BIT0_) ///< DVBT  enable bit
#define MI_SYS_DVBC_ENABLE  (_BIT1_) ///< DVBC  enable bit
#define MI_SYS_DVBS_ENABLE  (_BIT2_) ///< DVBS  enable bit
#define MI_SYS_ATSC_ENABLE  (_BIT3_) ///< ATSC  enable bit
#define MI_SYS_ISDB_ENABLE  (_BIT4_) ///< ISDB  enable bit
#define MI_SYS_DVBT2_ENABLE (_BIT5_) ///< DVBT2 enable bit
#define MI_SYS_DVBS2_ENABLE (_BIT6_) ///< DVBS2 enable bit
#define MI_SYS_DTMB_ENABLE  (_BIT7_) ///< DTMB  enable bit
#default dtv_type_support_list=0x67(MI_SYS_DVBS2_ENABLE | MI_SYS_DVBT2_ENABLE | MI_SYS_DVBS_ENABLE | MI_SYS_DVBC_ENABLE | MI_SYS_DVBT_ENABLE)
dtv_type_support_list=0x00

#customer_folder
customer_folder=$toptech_path/customer/$cus_id/TZ2109-164_ARE_LE32T1_EL_MT9255_FA48__CX509_GEEPAS

mboot_version="01-2L-SM-00-20200210"

#panel
#AU20_T200XW02.ini
#M190A1.ini(1400*900)
#FullHD_CMO216_H1L01_OSD_720P.ini
panelname=.ini

##################################
# Panel backlight related params #
##################################
#u32PeriodPWM=0x100      #DC-->0x100    PWM-->0xFFFF
#u32DutyPWM=0xCB         #DC-->0x7F     PWM-->0xF000
#bPolPWM=0               #0:NON_INVERSE 1:INVERSE
#u16MaxPWMvalue=0xCB     #DC-->0xFF(MAX)PWM-->0xFFFF
#u16MinPWMvalue=0x00

pwm_invert=off
pwm_max_current=600ma
pwm_min_current=100ma
pwm_frequency=47000
current_control=sw

#set density
lcd_density=240

#set mirror(0:Rotate 0, 1:Rotate 180)
mirror_on=0

#set mirror type
# 0:normal type.  1:Horizontal-mirror only.  2:Vertical-mirror only.3:HV-mirror.(default:3)
mirror_type=3

#set volumecurve
# 0:u8VolumeCurve(8O8W)
# 1:u8VolumeCurve(8O10W)
VolumeCurve=0

#ir option ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/ir/
ir_file=C001_509

#keypad option ,select from vendor/mediatek/proprietary_tv/open/system//customer/common/keypad/
keypad_file=keypad_ToptechPublic_oneKey

#bootlogo ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/bootlogo/
bootlogo_file=bootlogo_C001_GEEPAS

#bootanimation/bootvideo

#language and timezone
#the first launager is the default language,for more details,please see language_and_timezone.txt in doc folder
#en_US en_IN fr_FR it_IT es_ES et_EE de_DE nl_NL cs_CZ pl_PL ja_JP zh_TW zh_CN zh_HK 
#ru_RU ko_KR nb_NO es_US da_DK el_GR tr_TR pt_PT pt_BR rm_CH sv_SE bg_BG ca_ES en_GB fi_FI 
#hi_IN hr_HR hu_HU in_ID iw_IL lt_LT lv_LV ro_RO sk_SK sl_SI sr_RS uk_UA tl_PH ar_EG 
#fa_IR th_TH sw_TZ ms_MY af_ZA zu_ZA am_ET hi_IN en_XA ar_XB fr_CA km_KH lo_LA ne_NP si_LK mn_MN hy_AM az_AZ ka_GE no_NO
#es_CO vi_VN
language_list="en_US fr_FR ru_RU de_DE es_ES ar_EG pt_PT fa_IR it_IT"
timezone="Asia/Dubai"

#tuning country and dtv factory option
#AD:ANDORRA  AU:AUSTRALIA  AT:AUSTRIA  BE:BELGIUM  BW:BOTSWANA  BR:BRAZIL  CA:CANADA  CN:CHINA  HR:CROATIA
#CZ:CZECH  DK:DENMARK  EE:ESTONIA  FI:FINLAND  FR:FRANCE  DE:GERMANY  GR:GREECE  HU:HUNGARY  IN:INDIA  ID:INDONESIA
#IE:IRELAND  IT:ITALY  JP:JAPAN  LT:LITHUANIA  LU:LUXEMBOURG  MK:MACEDONIA  MT:MALTA  MY:MALAYSIA  MX:MEXICO  NL: 
#NO:NORWAY  PH:PHILIPPINES  RO:ROMANIA  PL:POLAND  PT:PORTUGAL  SG:SINGAPORE  SK:SLOVAKIA  SI:SLOVENIA  ES:SPAIN  SE:SWEDEN
#CH:SWITZERLAND  TW:TAIWAN  TH:THAILAND  TR:TURKEY  GB:UNITED_KINGDOM  US:USA  VN:VIETNAM  IR:IRAN  MA:MOROCCO  MM:MYANMAR
#NG:NIGERIA  GE:GEORGIA  KE:KENYA  AO:ANGOLA  MU:MAURITIUS  UZ:UZBEKISTAN  TZ:TANZANIA  AE:ARE  LA:LAO_PDR  MN:MONGOLIA
#KH:CAMBODIA  BD:BANGLADESH  LK:SRI_LANKA  NP:NEPAL  BN:BRUNEI  FJ:FIJI  MV:MALDIVES  IL:ISRAEL  AL:ALBANIA  DZ:ALGERIA
#AR:ARGENTINA  BZ:BELIZE  BO:BOLIVIA  BA:BOSNIA_HERCEGOVINA  BG:BULGARIA  CL:CHILE  CO:COLOMBIA  CR:COSTARICA  EC:ECUADOR
#EG:EGYPT  ET:ETHIOPIA  GH:GHANA  GT:GUATEMALA  HN:HONDURAS  IQ:IRAQ  KW:KUWAIT  LV:LATVIA  ME:MONTENEGRO  NI:NICARAGUA
#PK:PAKISTAN  PA:PANAMA  PY:PARAGUAY  PE:PERU  QA:QATAR  RU:RUSSIA  SV:SALVADOR  SA:SAUDI_ARABIA  RS:SERBIA  SY:SYRIA
#UA:UKRAINE  UY:URUGUAY  VE:VENEZUELA  AZ:AZERBAIJAN  DO:DOMINICAN
default_country=AE

#preinstall apk and remove apk
#preinstall_apk: Third-party applications
#remove_apk: such as PinyinIME.apk
preinstall_apk="ZeasnApp EShare GMS facebook apedroid"
remove_apk=""

#ota info

#####zeasn properties start
zeasn_devicetype=TopTechMTK368
zeasn_country=ARE
#####zeasn properties end

#default system volume
defaultVolume=30

#str enable 1:off 2:on
str_enable=2

#set play boot music enable:on off
play_music=off

#set apk install warning enable
installWarning=1

#Teletext enable 1 : on 0 : off
teletextEnable=1

#Memory launcher value : 0 off, 1 : Memory launcher, 2 : Memory TV
current_launcher=2
#ciEnable 1: on 0 : off
ciEnable=0
#setupwizardEnable 1 : on 0 : off
setupwizardEnable=1

#WiFi config
#SUPPORT_ODB_WLAN_LIST=MT7601U MT7662U MT7603U ATBM6022 RTL8188FTV RTL8723BU RTL8723DU
wifi_dongle=ATBM6022
atbm_wifi_cob_enable=true

#WiFi COB, write dcxo value to efuse data, efuse_data_dcxo_enable default false
efuse_data_dcxo_enable=false
efuse_data_dcxo=83

config_osd_reset_with_channels=false

#widevine enable 1:true   0:false
widevine_enable=true

#set dolby audio enable, 1: on    0:off
dolby_enable=0

#set Bluetoot enable, 1: on    0:off
BluetoothEnable=0
#ATV enable 1:on 0:off
ATV_enable=1

color_system=pal
sound_system=BG
#Virtual key position
config_str_keypad_position=below
#...
#...
###

#------------------------------------------------------------------
# defined in vendor/toptech/customer/common/overlay/*/configs.xml
config_default_menu_style="PublicTheme"
config_blue_screen_visible=true
config_hotel_visible=true
config_hdmi_mode_visible=true
#ECO MODE
config_shopmode_change_to_ecomode=true
config_shopmode_visible=true
config_shopmode_backlight=58
config_eco_mode_default_mode=3
config_default_backlight=85
config_eco_mode_four_options_backlight="100,90,80,58"
#电子屏贴
config_sticker_visible=false
config_sticker_enabled=false
config_sticker_location="start"
config_fac_reset_standby=true
config_ttx_language_visible=true
config_ttx_language_default=57
config_bootlogo_upgrade_enable=true
config_bootvideo_upgrade_enable=true
config_exit_aging_keycodes="KEYCODE_MENU,KEYCODE_FACTORY_EXIT_AGING,KEYCODE_POWER,KEYCODE_KEYPAD_ONEKEY"
#------------------------------------------------------------------

source $scripts_path/build_common.sh|| return 1;
echo "# end customer's script compiling"
