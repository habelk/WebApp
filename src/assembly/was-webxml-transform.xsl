<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    version="2.0" xmlns="http://java.sun.com/xml/ns/j2ee" exclude-result-prefixes="xs">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" />

    <!-- was-resource insertion point and dup check -->
    <xsl:variable name="was-resource-xpath-insert-point" as="element()">
        <xsl:sequence select="/*[1]/*[last()]" />
    </xsl:variable>
    <xsl:variable name="was-listener-xpath-insert-point" as="element()">
        <xsl:sequence select="/*[1]/*[name() eq 'listener'][last()]" />
    </xsl:variable>


    <xsl:variable name="was-resource-exists"
        select="/*[1]/*[name() eq 'resource-ref']/*[name() eq 'res-ref-name'][text() eq 'wm/DefaultWorkManager']" />


    <!-- process all elements, inject at specified xpaths if not already exists...identity transform the rest -->
    <xsl:template match="element()">
        <xsl:copy>
            <xsl:apply-templates select="@*,node()" />
        </xsl:copy>
        <xsl:if test=". is $was-listener-xpath-insert-point">
            </xsl:if>
        <xsl:if test=". is $was-resource-xpath-insert-point">
            <xsl:message>
                Injecting was resource entries
            </xsl:message>
 
        </xsl:if>
    </xsl:template>

    <xsl:template match="attribute()|text()|comment()|processing-instruction()">
        <xsl:copy />
    </xsl:template>
</xsl:stylesheet>