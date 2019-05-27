<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <body bgcolor="F1F1F1">
   <title> Organ Transplantation Database</title>
   <p><b><FONT FACE ="courier" SIZE ="8"> <center> -  TRANSPLANTATION DATABASE  -  </center> </FONT></b></p>
   <p><b><FONT FACE ="courier" SIZE ="6"> <center> -  DONORS  -  </center> </FONT></b></p>
   <table border="10" bordercolor="F1818A" cellspacing ="10" width ="60%" align="center">
      <th>Name</th>
      <th>Date of Birth</th>
      <th>Blood Type</th>
      <th>Location</th>
      <xsl:for-each select="Donor_List/Donor">
      <xsl:sort select="@name" />
         <tr bgcolor="#F8D7DF">
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><xsl:value-of select="datebirth" /></td>
            <td><xsl:value-of select="@bloodtype" /></td>
            <td><xsl:value-of select="@location" /></td>
         </tr>
      </xsl:for-each>
   </table>
   </body>
   </html>
</xsl:template>

</xsl:stylesheet>