package com.liufujun.game.me.pojo;

public class Panel {
    String Dual_MODE;
    String Mirror_MODE;
    String Ti_MODE;
    String Swap_MODE;
    String Bit_MODE;
    String M_PNAME;

    @Override
    public String toString() {
        return "Panel{" +
                "Dual_MODE='" + Dual_MODE + '\'' +
                ", Mirror_MODE='" + Mirror_MODE + '\'' +
                ", Ti_MODE='" + Ti_MODE + '\'' +
                ", Swap_MODE='" + Swap_MODE + '\'' +
                ", Bit_MODE='" + Bit_MODE + '\'' +
                ", M_PNAME='" + M_PNAME + '\'' +
                '}';
    }

    public String getM_PNAME() {
        return M_PNAME;
    }

    public void setM_PNAME(String m_PNAME) {
        M_PNAME = m_PNAME;
    }

    public String getDual_MODE() {
        return Dual_MODE;
    }

    public void setDual_MODE(String dual_MODE) {
        Dual_MODE = dual_MODE;
    }

    public String getMirror_MODE() {
        return Mirror_MODE;
    }

    public void setMirror_MODE(String mirror_MODE) {
        Mirror_MODE = mirror_MODE;
    }

    public String getTi_MODE() {
        return Ti_MODE;
    }

    public void setTi_MODE(String ti_MODE) {
        Ti_MODE = ti_MODE;
    }

    public String getSwap_MODE() {
        return Swap_MODE;
    }

    public void setSwap_MODE(String swap_MODE) {
        Swap_MODE = swap_MODE;
    }

    public String getBit_MODE() {
        return Bit_MODE;
    }

    public void setBit_MODE(String bit_MODE) {
        Bit_MODE = bit_MODE;
    }

    public class Ti_MODE {
        @Override
        public String toString() {
            return "Ti_MODE{" +
                    "on=" + on +
                    ", off=" + off +
                    '}';
        }

        int on;
        int off;

        public int getOn() {
            return on;
        }

        public void setOn(int on) {
            this.on = on;
        }

        public int getOff() {
            return off;
        }

        public void setOff(int off) {
            this.off = off;
        }
        public void input(String pd){
            if (pd.equals("1")){
                off=0;
                on=1;
            }else {
                off=1;
                on=0;
            }

        }
        public void input(int pd){
            if (pd==1){
                off=0;
                on=1;
            }else {
                off=1;
                on=0;
            }
        }
        public void delete(){
            off=0;
            on=0;
        }
    }
}
