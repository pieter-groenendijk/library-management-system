# Boete Betalen
<table> 
    <thead>
        <tr>
            <th scope="col" colspan="2">Section</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">Primary Actor</th>
            <td>Lid</td>
        </tr>
        <tr>
            <th scope="row">Stakeholders & Interests</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Cross References</th>
            <td>FR-034</td>
        </tr>
        <tr>
            <th scope="row">Brief Description</th>
            <td>Als lid, wil ik mijn boetes kunnen betalen.</td>
        </tr>
        <tr>
            <th scope="row">Preconditions</th>
        </tr>
        <tr>
            <th scope="row">Postconditions on Success</th>
            <td>1. Er is geen openstaande boete.<br></td>
        </tr>
        <tr>
            <th scope="row">Postconditions on Failure</th>
            <td></td>
        </tr>
        <tr>
            <th scope="row">Main Success Scenario (Basic Flow)</th>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1. Lid opent overzicht boetes<br>2.Lid drukt op "Pay"</td>
                            <td>
                                3. Systeem verwerkt betaling (niet geïmplementeerd)<br>
                                3. Systeem registreert betaling en past boetes aan.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <th scope="row">Alternate Flow</th>
            <td>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td>2.A Er staan geen boetes open.<br></td>
                            <td>
                            </td>
                        </tr>
                    </tbody>
                </table></td>
        </tr>
        <tr>
            <th scope="row">Exceptional Flows</th>
            <td>
                <div>Schuldinformatie onbereikbaar</div>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">User</th>
                            <th scope="col">System</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <tr>
                            <td></td>
                            <td>
                                2.B Systeem geeft een foutmelding aan dat de openstaande boete(s) niet opgehaald kan worden.<br>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                2.C Betalingssysteem niet bereikbaar.<br>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </tbody>
</table>
