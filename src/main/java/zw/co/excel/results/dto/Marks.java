/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.excel.results.dto;

/**
 *
 * @author shepherd
 */
public class Marks {

    public Marks() {
    }
    
    

    public double ca;
    public double ex;
    public double om;
    public String moduleCode;
    private String yos ;
    private String sos ;

    /**
     * @return the ca
     */
    public double getCa() {
        return ca;
    }

    /**
     * @param ca the ca to set
     */
    public void setCa(double ca) {
        this.ca = ca;
    }

    /**
     * @return the ex
     */
    public double getEx() {
        return ex;
    }

    /**
     * @param ex the ex to set
     */
    public void setEx(double ex) {
        this.ex = ex;
    }

    /**
     * @return the om
     */
    public double getOm() {
        return om;
    }

    /**
     * @param om the om to set
     */
    public void setOm(double om) {
        this.om = om;
    }

    /**
     * @return the moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @param moduleCode the moduleCode to set
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marks{ca=").append(ca);
        sb.append(", ex=").append(ex);
        sb.append(", om=").append(om);
        sb.append(", moduleCode=").append(moduleCode);
        sb.append(", yos=").append(yos);
        sb.append(", sos=").append(sos);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return the yos
     */
    public String getYos() {
        return yos;
    }

    /**
     * @param yos the yos to set
     */
    public void setYos(String yos) {
        this.yos = yos;
    }

    /**
     * @return the sos
     */
    public String getSos() {
        return sos;
    }

    /**
     * @param sos the sos to set
     */
    public void setSos(String sos) {
        this.sos = sos;
    }

}
