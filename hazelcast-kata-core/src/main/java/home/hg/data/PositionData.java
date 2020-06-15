package home.hg.data;

import java.io.Serializable;

/**
 * Created by HARISH on 07- 07- 2019
 */
public class PositionData implements Serializable {

    private Long termNumber;
    private String positionType;
    private String costType;
    private Double riskMTM;

    public PositionData(Long termNumber, String positionType, String costType, Double riskMTM) {
        this.termNumber = termNumber;
        this.positionType = positionType;
        this.costType = costType;
        this.riskMTM = riskMTM;
    }

    public Long getTermNumber() {
        return termNumber;
    }

    public String getPositionType() {
        return positionType;
    }

    public String getCostType() {
        return costType;
    }

    public Double getRiskMTM() {
        return riskMTM;
    }
}
