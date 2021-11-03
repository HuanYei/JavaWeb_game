#!/bin/bash

curpath=${BASH_SOURCE[0]}
curpath=$(dirname ${curpath})
. ${curpath}/../../../customer/scripts/init.sh


#######################################################
#######################################################
# sw config, require by customer.
config_software_revision=V1.0.01
# string, customer short name
config_customer_name="C001"
# string, product full name
config_product_name="LE50N1"
# string, customer configs folder name
config_customer_folder_name="ST2007-028_TURKEY_LE50N1_P150_2851V60_NOTV_CC500PV5D_CX-509"
# string, panel name, only[0-9a-zA-Z_]
config_panel_name="C001_CC500PV5D"
config_pcb_name=P150_2851V60
# config for sw setting
config_pcb_varient=P150_2851V60_A
config_keypad_name=C001_7KEY
config_use_smart_keyboard=0
# define in kernel\android\nougat\device\realtek\app\DTVInput\src\com\realtek\dtv\setup\Country.java
#ANDORRA AUSTRALIA AUSTRIA BELGIUM BOTSWANA CHINA CROATIA CZECH DENMARK ESTONIA FINLAND FRANCE GERMANY HUNGARY INDIA INDONESIA IRELAND ITALY LITHUANIA LUXEMBOURG MACEDONIA MALTA MALAYSIA NETHERLANDS NORWAY ROMANIA POLAND PORTUGAL SINGAPORE SLOVAKIA SLOVENIA SPAIN SWEDEN SWITZERLAND TAIWAN THAILAND TURKEY UNITED_KINGDOM VIETNAM IRAN MOROCCO MYANMAR NIGERIA GEORGIA KENYA ANGOLA MAURITIUS UZBEKISTAN TANZANIA ARE LAO_PDR MONGOLIA CAMBODIA BANGLADESH SRI_LANKA NEPAL BRUNEI FIJI MALDIVES ISRAEL EGYPT ASIA_NONE CANADA USA MEXICO BRAZIL JAPAN PHILIPPINES
config_default_country="TURKEY"

#en_US en_IN fr_FR it_IT es_ES et_EE de_DE nl_NL cs_CZ pl_PL ja_JP zh_TW zh_CN zh_HK 
#ru_RU ko_KR nb_NO es_US da_DK el_GR tr_TR pt_PT pt_BR rm_CH sv_SE bg_BG ca_ES en_GB fi_FI 
#hi_IN hr_HR hu_HU in_ID iw_IL lt_LT lv_LV ro_RO sk_SK sl_SI sr_RS uk_UA tl_PH ar_EG 
#fa_IR th_TH sw_TZ ms_MY af_ZA zu_ZA am_ET hi_IN en_XA ar_XB fr_CA km_KH lo_LA ne_NP si_LK mn_MN hy_AM az_AZ ka_GE
#es_CO vi_VN
config_product_locales="tr_TR fr_FR es_ES it_IT ru_RU de_DE ar_EG en_US"

# use tzselect to get the timezone
config_default_timezone="Europe/Istanbul"
config_no_dtv=true
# atsc isdb dvbt dtmb
config_default_dtv_system=dvbt
# pal ntsc
config_default_atv_system=pal
# 0 1
config_default_enable_dvbc=0
config_default_enable_dvbs=0
config_no_atv=true

#true false
config_default_enable_ci=0
config_default_enable_mheg5=0
config_default_enable_tt=0

# on/off
config_pwm_invert=off
config_pwm_max_current=600ma
config_pwm_min_current=100ma
config_pwm_frequency=47000
config_current_control=sw
config_amp_power=8o8w_26db

config_lcn_option=0
config_hotel_enabled=false

config_show_blue_screen=1
config_prebuild_modules="zhixiangUI_NAT GMS Keyboard_seven IPTV"
config_remove_modules="TopLauncher SetupWizard"
config_not_reuse_subtitle=0
#nullable
config_remote_name=C001_CX509_PAL_T2_SKT_DVBS
config_bootlogo_name=C001_NAVITECH_1920x1080_bootfile.raw
config_bootvideo_name=
config_launcher_logo=C001_DIJITSU.jpg
config_tv_reset_with_tv_programs=false
config_tv_reset_with_source_programs=false
#non null
#config_preset_videos="HYUNDAI_STORE_GUIDE.mp4 APTOIDE_GUIDE.mp4"
config_c001_onekeypad=1

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
toptech_eshare_enable=1
config_bluetooth_enable=0
# unspecified: -1; landscape: 0; portrait: 1;
config_force_screen_orientation=0



#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
config_after_delete_channel_resort=true
config_hdmi_mode_visible=false
config_miracast_name=SMART_TV
config_brand_name=SMART_TV
config_device_name=SMART_TV
config_model_name=SMART_TV
# defined in 'customer/default/overlay/com.toptech.tvmenu/configs.xml'
config_default_menu_style="PublicTheme"
config_hotel_osd_lock_visible="true"
config_ttx_language_visible=true
#0:west europe 1:east europe 2:Russian 3:Arabic 4:Farsi
config_ttx_language_default=3
config_tv_reset_with_hotelmode=false
config_osd_mute_color_enabled=true
config_hotel_visible=true
config_channel_edit_swap_visible=false
config_ecomode_visible=true
config_ecomode_enabled=false
config_ecomode_backlight=70
# current launcher 0:off 1:launcher 2:TopTvMenu
config_current_launcher=1
config_factoryreset_inlauncher=true

config_lock_channel_visible=false
config_hotel_scan_lock_visible=false
config_hotel_channel_type_visible=false
config_hotel_default_channel_visible=false
config_hotel_import_emport_visible=false
config_hotel_osd_function_program_visible=false
config_devicetype_Zeasn=TopTechTv2851_NAT

#µç×ÓÆÁÌù
config_sticker_visible=false
config_sticker_enabled=false
config_sticker_location="start"

config_select_country_before_scan=true
config_new_epg_style_enabled=true
#+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#default auto,other ntsc pal secam
config_color_system=auto
#default BG, I DK L M N 
config_sound_system=BG
config_turksat_satellite_top=1
config_IsNatNoTv_enable=true
#default    0:NONE;  1:LNB1;   2:LNB2    3:LNB3   4:LNB4    
#vendor\realtek\frameworks\native\appclass\MediaControl\Component\Satellite\SatelliteDataSet.cpp
#config_satellite_lnb_diseqc10_port=1

. ${curpath}/../topcompile.sh
