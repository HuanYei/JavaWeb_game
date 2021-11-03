#!/bin/bash

curpath=${BASH_SOURCE[0]}
curpath=$(dirname ${curpath})
. ${curpath}/../../../customer/scripts/init.sh


#######################################################
#######################################################
# sw config, require by customer.
config_software_revision=V1.0.01
# string, customer short name
config_customer_name="A049"
# string, product full name
config_product_name="TURKEY_LSC550FN11_1"
# string, customer configs folder name
config_customer_folder_name="TURKEY_P150_2851V62_DVBT_C_S2_LSC550FN11_1"
# string, panel name, only[0-9a-zA-Z_]
config_panel_name="LSC550FN11_1"
config_pcb_name=P150_2851V62
# config for sw setting
# origin: config_pcb_varient=P150_2851V62
config_pcb_varient=P150_2851V62_A
config_keypad_name=DEMO_7KEY
config_use_smart_keyboard=0
# define in kernel\android\nougat\device\realtek\app\DTVInput\src\com\realtek\dtv\setup\Country.java
#ANDORRA AUSTRALIA AUSTRIA BELGIUM BOTSWANA CHINA CROATIA CZECH DENMARK ESTONIA FINLAND FRANCE GERMANY HUNGARY INDIA INDONESIA IRELAND ITALY LITHUANIA LUXEMBOURG MACEDONIA MALTA MALAYSIA NETHERLANDS NORWAY ROMANIA POLAND PORTUGAL SINGAPORE SLOVAKIA SLOVENIA SPAIN SWEDEN SWITZERLAND TAIWAN THAILAND TURKEY UNITED_KINGDOM VIETNAM IRAN MOROCCO MYANMAR NIGERIA GEORGIA KENYA ANGOLA MAURITIUS UZBEKISTAN TANZANIA ARE LAO_PDR MONGOLIA CAMBODIA BANGLADESH SRI_LANKA NEPAL BRUNEI FIJI MALDIVES ISRAEL EGYPT ASIA_NONE CANADA USA MEXICO BRAZIL JAPAN PHILIPPINES
config_default_country="TURKEY"

#en_US en_IN fr_FR it_IT es_ES et_EE de_DE nl_NL cs_CZ pl_PL ja_JP zh_TW zh_CN zh_HK 
#ru_RU ko_KR nb_NO es_US da_DK el_GR tr_TR pt_PT pt_BR rm_CH sv_SE bg_BG ca_ES en_GB fi_FI 
#hi_IN hr_HR hu_HU in_ID iw_IL lt_LT lv_LV ro_RO sk_SK sl_SI sr_RS uk_UA tl_PH ar_EG 
#fa_IR th_TH sw_TZ ms_MY af_ZA zu_ZA am_ET hi_IN en_XA ar_XB fr_CA km_KH lo_LA ne_NP si_LK mn_MN hy_AM az_AZ ka_GE
#es_CO vi_VN
config_product_locales="en_US de_DE fr_FR ru_RU it_IT es_ES pt_PT sr_RS ar_EG"

# use tzselect to get the timezone
config_default_timezone="Europe/Athens"
# atsc isdb dvbt dtmb
config_default_dtv_system=dvbt
# pal ntsc
config_default_atv_system=pal
# 0 1
config_default_enable_dvbc=1
config_default_enable_dvbs=1
#true false
config_default_enable_ci=1
config_default_enable_mheg5=0
config_default_enable_tt=1

config_default_launcher="com.toptech.tvmenu\/.MainActivity"

# on/off
config_pwm_invert=off
config_pwm_max_current=600ma
config_pwm_min_current=100ma
config_pwm_frequency=47000
config_current_control=sw
config_amp_power=8o8w_26db

config_show_blue_screen=1
config_prebuild_modules="zhixiangUI GMS"
config_remove_modules="TopLauncher LiveTv"
#nullable
config_remote_name=AT073
config_bootlogo_name=Public_SmartTV_bootfile.raw
config_bootvideo_name=
config_launcher_logo=smartTV.jpg
config_tv_reset_with_tv_programs=false
#non null
#config_preset_videos="HYUNDAI_STORE_GUIDE.mp4 APTOIDE_GUIDE.mp4"
#0:picture 1:black 2:white 3:red 4:blue 5:green
config_mute_color=1
# 0,1
# use the bootcode in customer folder or not.
use_prebuilt_bootcode=0
config_dolby_able=1
#off: always powerOff, on: always powerOn, save:auto by prev_status
config_power_on_mode=off
# auto upgrade after power on
config_auto_upgrade=0
config_default_backlight=100
#default auto,other ntsc pal secam
#config_color_system= 
#off:Close LOGO , on: Show LOGO
config_logo_enable=on
toptech_eshare_enable=0
config_bluetooth_enable=0
# unspecified: -1; landscape: 0; portrait: 1;
config_force_screen_orientation=0

config_auto_standby_visible=true
config_auto_standby_low=true
config_auto_standby_default=2
config_subtitle_hi_visible=true
config_subtitle_hi_enable=false
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# defined in 'customer/default/overlay/com.toptech.tvmenu/configs.xml'
config_default_menu_style="PublicTheme"
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

. ${curpath}/../topcompile.sh
