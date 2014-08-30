<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head><title>Submit example</title></head>
<body>

	<h3>Example Submit Page</h3>
	
	<html:errors/>
	
	<html:form method="post" action="submit.do">
        <table border="0">
            <tr>
                <td width="35">
                    <bean:message key="personalData.lastname"/>
                </td>
                <td>
                    <html:text property="lastname" size="30"/>
                </td>
            </tr>
            <tr>
                <td width="35">
                    <bean:message key="personalData.address"/>
                </td>
                <td>
                    <html:text property="address" size="80"/>
                </td>
            </tr>
            <tr>
                <td width="35">
                    <bean:message key="personalData.sex"/>
                </td>
                <td>
                    <html:radio property="sex" value="M"/>Male 
		           <html:radio property="sex" value="F"/>Female
                </td>
            </tr>
            <tr>
                <td width="35">
                    <bean:message key="personalData.married"/>
                </td>
                <td>
                    <html:checkbox property="married"/>
                </td>
            </tr>
            <tr>
                <td width="35">
                    <bean:message key="personalData.age"/>
                </td>
                <td>
                    <html:select property="age">
		             <html:option value="a">0-19</html:option>
		             <html:option value="b">20-49</html:option>
		             <html:option value="c">50-</html:option>
		           </html:select>
                </td>
            </tr>
        </table>

        <br />

        <html:submit>
            <bean:message key="personalData.add.submit"/>
        </html:submit>
	</html:form>
	
	<logic:present name="lastName" scope="request">
		Hello
		<logic:equal name="submitForm" property="age" value="a">
		  young
		</logic:equal>
		<logic:equal name="submitForm" property="age" value="c">
		  old
		</logic:equal>
		<bean:write name="lastName" scope="request" />
	</logic:present>

</body>
</html>

