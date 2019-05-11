package com.dong.nfcsecurity.db.model;


/**
 * <p>
 * CREATE TABLE IF NOT EXISTS  `jxs_table`(
 * `jxsId` TEXT PRIMARY,
 * `jxsName` TEXT,
 * `jhDate` DATETIME,
 * `chDate` DATETIME,
 * `hzpId` TEXT
 * );
 */
public class Decoration {

    private String id;
    private String name;
    private String produceDate;
    private String expiration;
    private Factory factory;
    private Security security;
    private Reseller reseller;

    @Override
    public String toString() {
        return "Decoration{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", produceDate='" + produceDate + '\'' +
                ", expiration='" + expiration + '\'' +
                ", factory=" + factory +
                ", security=" + security +
                ", reseller=" + reseller +
                '}';
    }

    public static class Factory {

        private String id;
        private String name;
        private String address;
        private String telephone;
        private String productDate;

        @Override
        public String toString() {
            return "Factory{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", productDate='" + productDate + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getProductDate() {
            return productDate;
        }

        public void setProductDate(String productDate) {
            this.productDate = productDate;
        }
    }

    public static class Security {
        private int no;
        private String labelID;
        private boolean check;

        @Override
        public String toString() {
            return "Security{" +
                    "no=" + no +
                    ", labelID='" + labelID + '\'' +
                    ", check=" + check +
                    '}';
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getLabelID() {
            return labelID;
        }

        public void setLabelID(String labelID) {
            this.labelID = labelID;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }

    public static class Reseller {
        private String id;
        private String name;
        private String inDate;
        private String outDate;

        @Override
        public String toString() {
            return "Reseller{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", inDate='" + inDate + '\'' +
                    ", outDate='" + outDate + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInDate() {
            return inDate;
        }

        public void setInDate(String inDate) {
            this.inDate = inDate;
        }

        public String getOutDate() {
            return outDate;
        }

        public void setOutDate(String outDate) {
            this.outDate = outDate;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }
}
