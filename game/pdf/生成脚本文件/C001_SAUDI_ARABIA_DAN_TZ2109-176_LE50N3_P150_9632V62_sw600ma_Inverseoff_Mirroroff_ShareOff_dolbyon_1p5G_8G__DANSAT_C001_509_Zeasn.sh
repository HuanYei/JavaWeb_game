#!/bin/bash

echo "#------------------------------------------------------------------------------"
echo "# start customer's script compiling..."

# 6681 > 1G: m5621 1.5G: m5621_1p5g
# 9632 > 1.5G: m7332_eu 1G: m7332_eu_1g 2G: m7332_eu_2g
build_product="m7332_eu"

#chip_name
CHIP_NAME=m7332

#board_name
BOARD_NAME=BD_MT165B_10AT_19055_4K

#mboot path
#MTK6681P => 151B_optee_MTK_32bit
#mtk9632p => android.MST165B_10AT_19055.rom_emmc.optee.32bit_cn19
#mboot_bin_path=android.MST165B_10AT_19055.rom_emmc.optee.32bit_cn19

#pcb_type
# MTK PCB BOARD
# 1、MTK_PCB_BOARD_MT6681P
# 2、MTK_PCB_BOARD_MT9632P

# Toptech PCB BOARD
# 1、Toptech_PCB_BOARD_P75_6681V62
# 2、Toptech_PCB_BOARD_P150_9632V62
# 3、Toptech_PCB_BOARD_MT9632V63
pcb_board_type=Toptech_PCB_BOARD_P150_9632V62
#Toptech_PCB_BOARD_P150_9632V62A:AV IN, mini YPBPR
#Toptech_PCB_BOARD_P150_9632V62B:SCART, mini AV IN
#Toptech_PCB_BOARD_P150_9632V62C:AV IN
config_pcb_varient=Toptech_PCB_BOARD_P150_9632V62
# string, customer short name
cus_id="C001"
# string, product full name
# this value will include into OTA package name
cus_pro_id="LE50N3_P150_9632V62_TZ2109-176"
# string, software revision
software_revision="V1.0.01"
# string pcb name,must keep the same with the defination in customer script name
pcbname="P150_9632V62"

#ddr_type 0:512M	1:1G  2:1.5G
ddr_type=2

#flash_type
#0:4G EMMC
#1:8G EMMC
#2:16G EMMC
use_flash_size_type=1

#tuner and demodulator option
#tuner driver define at Frontend_Enum_Device.h
#The DVBC/DVBT/ISDBTJ83B/ATSC/ATV commonly used: TUNER_R842 TUNER_MXL661
#The DVBS/S2 commonly used: TUNER_AV2017 TUNER_AV2012(same as TUNER_AV2017) TUNER_RDA5815M
tuner=TUNER_R842
s_tuner=TUNER_RDA5815M

# xtal setting: 1 -> R842_SLAVE_XTAL_OUT(shared Crystal Oscillator), 0 -> R842_NO_SHARE_XTAL(independed Crystal Oscillator)
R842_SHARE_XTAL=0

#Check Tuner enable : default:false
CheckTunerEnable=false

#Tuner RF Gain enable : default:false
TunerRfGainEnable=false

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
dtv_type_support_list=0x67

#customer_folder
export customer_folder=$toptech_path/customer/$cus_id/TZ2109-176_SAUDI_ARABIA_LE50N3_P150_9632V62__CX509_DANSAT

#panel
#AU20_T200XW02.ini
#M190A1.ini(1400*900)
#FullHD_CMO216_H1L01.ini
#UD_VB1_8LANE.ini
panelname=.ini

##################################
# Panel backlight related params #
##################################
#pwm_invert=off         #off:NON_INVERSE on:INVERSE
#pwm_max_current=600ma
#pwm_min_current=135ma  #min backlight
#pwm_frequency=47000    #DC-->47000     PWM-->183
#current_control=sw     #DC-->sw        PWM-->hw
pwm_invert=off
pwm_max_current=600ma
pwm_min_current=135ma
pwm_frequency=47000
current_control=sw

#set swing_level
swing_level=250

#set density
lcd_density=320

#set mirror(0:Rotate 0, 1:Rotate 180)
mirror_on=0

#set mirror type, only valid if mirror_on is 1
# 0:normal type.  1:Horizontal-mirror only.  2:Vertical-mirror only.3:HV-mirror.(default:3)
mirror_type=3

#set volumecurve
# 0:u8VolumeCurve(8O8W)
# 1:u8VolumeCurve(8O10W)
# 2:u8VolumeCurve(4O3W)
# 3:u8VolumeCurve(8O5W)
# 4:u8VolumeCurve(8O6W)
VolumeCurve=0

#ir option ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/ir/
ir_file=C001_509

#keypad option ,select from vendor/mediatek/proprietary_tv/open/system//customer/common/keypad/
keypad_file=keypad_ToptechPublic_oneKey

#bootlogo ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/bootlogo/
bootlogo_file=bootlogo_C001_DANSAT

#bootanimation ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/bootanimation/
#Notice:Only one of them can be used for bootanimation and bootvideo
bootanimation_file=

#bootvideo ,select from vendor/mediatek/proprietary_tv/open/system/customer/common/bootvideo/
#Notice:Only one of them can be used for bootanimation and bootvideo
bootvideo_file=

#language and timezone
#the first launager is the default language,for more details,please see language_and_timezone.txt in doc folder
#en_US en_IN fr_FR it_IT es_ES et_EE de_DE nl_NL cs_CZ pl_PL ja_JP zh_TW zh_CN zh_HK 
#ru_RU ko_KR nb_NO es_US da_DK el_GR tr_TR pt_PT pt_BR rm_CH sv_SE bg_BG ca_ES en_GB fi_FI 
#hi_IN hr_HR hu_HU in_ID iw_IL lt_LT lv_LV ro_RO sk_SK sl_SI sr_RS uk_UA tl_PH ar_EG 
#fa_IR th_TH sw_TZ ms_MY af_ZA zu_ZA am_ET hi_IN en_XA ar_XB fr_CA km_KH lo_LA ne_NP si_LK mn_MN hy_AM az_AZ ka_GE no_NO
#es_CO vi_VN
language_list="en_US fr_FR ru_RU ar_EG fa_IR tr_TR es_ES"
timezone="Asia/Riyadh"

#tuning country and dtv factory option
#AD:ANDORRA  AU:AUSTRALIA  AT:AUSTRIA  BE:BELGIUM  BW:BOTSWANA  BR:BRAZIL  CA:CANADA  CN:CHINA  HR:CROATIA
#CZ:CZECH  DK:DENMARK  EE:ESTONIA  FI:FINLAND  FR:FRANCE  DE:GERMANY  GR:GREECE  HU:HUNGARY  IN:INDIA  ID:INDONESIA
#IE:IRELAND  IT:ITALY  JP:JAPAN  LT:LITHUANIA  LU:LUXEMBOURG  MK:MACEDONIA  MT:MALTA  MY:MALAYSIA  MX:MEXICO  NL:NETHERLANDS
#NO:NORWAY  PH:PHILIPPINES  RO:ROMANIA  PL:POLAND  PT:PORTUGAL  SG:SINGAPORE  SK:SLOVAKIA  SI:SLOVENIA  ES:SPAIN  SE:SWEDEN
#CH:SWITZERLAND  TW:TAIWAN  TH:THAILAND  TR:TURKEY  GB:UNITED_KINGDOM  US:USA  VN:VIETNAM  IR:IRAN  MA:MOROCCO  MM:MYANMAR
#NG:NIGERIA  GE:GEORGIA  KE:KENYA  AO:ANGOLA  MU:MAURITIUS  UZ:UZBEKISTAN  TZ:TANZANIA  AE:ARE  LA:LAO_PDR  MN:MONGOLIA
#KH:CAMBODIA  BD:BANGLADESH  LK:SRI_LANKA  NP:NEPAL  BN:BRUNEI  FJ:FIJI  MV:MALDIVES  IL:ISRAEL  AL:ALBANIA  DZ:ALGERIA
#AR:ARGENTINA  BZ:BELIZE  BO:BOLIVIA  BA:BOSNIA_HERCEGOVINA  BG:BULGARIA  CL:CHILE  CO:COLOMBIA  CR:COSTARICA  EC:ECUADOR
#EG:EGYPT  ET:ETHIOPIA  GH:GHANA  GT:GUATEMALA  HN:HONDURAS  IQ:IRAQ  KW:KUWAIT  LV:LATVIA  ME:MONTENEGRO  NI:NICARAGUA
#PK:PAKISTAN  PA:PANAMA  PY:PARAGUAY  PE:PERU  QA:QATAR  RU:RUSSIA  SV:SALVADOR  SA:SAUDI_ARABIA  RS:SERBIA  SY:SYRIA
#UA:UKRAINE  UY:URUGUAY  VE:VENEZUELA  AZ:AZERBAIJAN  DO:DOMINICAN
default_country=SA

# fac_test_index:
# FACTORY_TEST_FREQ_DVBT2_6M=0
# FACTORY_TEST_FREQ_DVBT2_8M=1
# FACTORY_TEST_FREQ_DVBT_6M=2
# FACTORY_TEST_FREQ_DVBT_8M=3
# FACTORY_TEST_FREQ_DTMB_8M=4
fac_test_index=1

#preinstall apk and remove apk
#preinstall_apk: Third-party applications
#remove_apk: such as PinyinIME.apk
preinstall_apk="ZeasnUI EShare KeyBoard_Seven apedroid twitter Facebook"
remove_apk="Launcher3"

#ota info

#####zeasn properties start
zeasn_devicetype=TopTechMTK9632
zeasn_country=SAUDI_ARABIA
#####zeasn properties end

#default system volume
defaultVolume=30

#str enable 1:off 2:on
str_enable=2

#set play boot music enable:on off. not support
#play_music=off

#set subtitle enable, 1: on    0:off
subtitle_enable=1

#set apk install warning enable
installWarning=1

#Teletext enable 1 : on 0 : off
teletextEnable=1

#setupHomeTv default value : 0 Home, 1 2 : TV
current_launcher=0
#ciEnable 1: on 0 : off
ciEnable=1

#setupwizardEnable 1 : on 0 : off
setupwizardEnable=1

#WiFi config
#SUPPORT_ODB_WLAN_LIST=MT7601U MT7662U MT7668U MT7603U ATBM6022(compatible ATBM6032) RTL8188FTV RTL8723BU RTL8723DU RTL8192EU
wifi_dongle=RTL8188FTV
atbm_wifi_cob_enable=true

#Bluetooth Enable 1:on  0:off
#SUPPORT_BLUETOOTH_LIST=MT7668U RTL8723BU RTL8723DU
BluetoothEnable=0


#widevine enable 1:true   0:false
#widevine_enable=true
#set dolby audio enable, true: on    false:off
dolby_enable=true

#Hashkey Type
#Hashkey path:vendor\toptech\customer\common\hashkey\m7332
#Attention!!!:When the hashkey supports dobly, you need to set [dolby_enable] to be 'true'.
#Attention!!!:When switching hasheky, the code needs to be cleaned & fully compiled, otherwise it won't work.
#0:    Domestic_NoDD_1G
#LICENSES:DRA, GAAC (Decoder), MPEG2, MPEG2_HD, MPEG4, MPEG4_HD, MPEG4_SD, MVC, H.264, Miracast, AVS, AVS2, FLV, HEVC, VC1

#1:    Domestic_NoDD_1p5G
#LICENSES:DRA, GAAC (Decoder), MPEG2, MPEG2_HD, MPEG4, MPEG4_HD, MPEG4_SD, MVC, H.264, Miracast, AVS, AVS2, FLV, HEVC, VC1

#2:    Export_NoDD
#LICENSES:GAAC (Decoder), PlayReady, Widevine, MPEG2, MPEG2_HD, MPEG4, MPEG4_HD, MPEG4_SD, MVC, H.264, Miracast, FLV, HEVC, VC1, VP8, VP9

#3:    Export_DD
#LICENSES:GAAC (Decoder), PlayReady, Widevine, DD, DD+, MPEG2, MPEG2_HD, MPEG4, MPEG4_HD, MPEG4_SD, MVC, H.264, Miracast, FLV, HEVC, VC1, VP8, VP9

#4:    Export_DD_HBBTV
#LICENSES:GAAC (Decoder), PlayReady, Widevine, DD, DD+, MPEG2, MPEG2_HD, MPEG4, MPEG4_HD, MPEG4_SD, MVC, H.264, iWedia Engine, Miracast, Web platform HbbTV 2.0/FVP, FLV, HEVC, VC1, VP8, VP9
hashkey_type=3

#ATV enable 1:on 0:off
ATV_enable=1

#MHEG5 Enable 1:on  0:off
mheg5_enable=0

#ATV default settings
color_system=PAL
sound_system=BG

#setupScan country show:1 hide:0
setupScanEnable=1

#setupHomeTv select show:1 hide:0
#setupHomeTvEnabled=1

#mousekeybord
config_mousekeybord_enable=false

#upgrade_auto_wakeup 1:wakeup 0:default(base on factory_poweron_mode)
upgrade_auto_wakeup=0

#Osd Reset Channels enable default:true
osdResetWithChannels=false
#...
###

#------------------------------------------------------------------
# defined in vendor/toptech/customer/common/overlay/*/configs.xml
config_default_menu_style="PublicTheme"

config_select_country_before_scan=true

config_hotel_visible=true

config_blue_screen_visible=true
#TTX
config_ttx_language_visible=true
#0：west 1：east 2：russian 7：farsi 8：arabic
config_ttx_language_default=8
config_teletext_back=false
# PVR
config_pvr_function_enabled=true
config_pvr_record_file_play_next=true
#弱视弱听
config_subtitle_HOH_visible=true
config_AD_Switch_visible=true
#default_AD_Switch_Volume
config_default_ADVolume=80

config_hdmi_mode_visible=true

config_auto_standby_visible=true
config_auto_standby_low=true

# new epg style
config_new_epg_style_enabled=true

#move change to swap
config_channel_edit_swap_visible=true


config_rec_play_enable=true

config_epg_timer_countdown=20

#电子屏贴
config_sticker_visible=false
config_sticker_enabled=false
config_sticker_location="start"

#ECO MODE
config_show_eco_mode_four_options=false
config_shopmode_change_to_ecomode=true
config_shopmode_visible=true
config_shopmode_enabled=false
config_shopmode_backlight=60
config_factory_android_reset=false
config_android_reset_enable=true


#3:SVL_ID_GENERALE_SATELITE    4:SVL_ID_PREFERRED_SATELITE
config_dvbs_scan_type=3
#------------------------------------------------------------------
source $scripts_path/build_common.sh || return 1;
echo "# end customer's script compiling"
