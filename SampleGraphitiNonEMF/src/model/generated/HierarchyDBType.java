//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.19 at 11:52:08 AM IST 
//


package model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HierarchyDBType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HierarchyDBType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HierarchyList" type="{http://www.example.org/hierarchy}HierarchyListType"/>
 *         &lt;element name="PersonList" type="{http://www.example.org/hierarchy}PersonListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HierarchyDBType", propOrder = {
    "hierarchyList",
    "personList"
})
@XmlRootElement
public class HierarchyDBType {

    @XmlElement(name = "HierarchyList", required = true)
    protected HierarchyListType hierarchyList;
    @XmlElement(name = "PersonList", required = true)
    protected PersonListType personList;

    /**
     * Gets the value of the hierarchyList property.
     * 
     * @return
     *     possible object is
     *     {@link HierarchyListType }
     *     
     */
    public HierarchyListType getHierarchyList() {
        return hierarchyList;
    }

    /**
     * Sets the value of the hierarchyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link HierarchyListType }
     *     
     */
    public void setHierarchyList(HierarchyListType value) {
        this.hierarchyList = value;
    }

    /**
     * Gets the value of the personList property.
     * 
     * @return
     *     possible object is
     *     {@link PersonListType }
     *     
     */
    public PersonListType getPersonList() {
        return personList;
    }

    /**
     * Sets the value of the personList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonListType }
     *     
     */
    public void setPersonList(PersonListType value) {
        this.personList = value;
    }

}
