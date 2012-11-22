/**
 * ${licence}
 */
package ${packageName};

${imports}

import javax.annotation.Generated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * <h2>standard jpa demo</h2>
 * <a href="http://static.springsource.org/spring-data/data-jpa/docs/1.0.0.M1/reference/html/#repositories.custom-implementations">http://static.springsource.org/spring-data/data-jpa/docs/1.0.0.M1/reference/html/#repositories.custom-implementations</a>
 * <table summary="Supported keywords inside method names" border="1">
 * <colgroup><col><col><col></colgroup><thead>
 * <tr>
 * <th>Keyword</th>
 * <th>Sample</th>
 * <th>JPQL snippet</th>
 * </tr>
 * </thead><tbody>
 * <tr>
 * <td><code class="code">And</code></td>
 * <td><code class="code">findByLastnameAndFirstname</code></td>
 * <td><code class="code">бн where x.lastname = ?1 and x.firstname =
                ?2</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">Or</code></td>
 * <td><code class="code">findByLastnameOrFirstname</code></td>
 * <td><code class="code">бн where x.lastname = ?1 or x.firstname =
                ?2</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">Between</code></td>
 * <td><code class="code">findByStartDateBetween</code></td>
 * <td><code class="code">бн where x.startDate between 1? and
                ?2</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">LessThan</code></td>
 * <td><code class="code">findByAgeLessThan</code></td>
 * <td><code class="code">бн where x.age &lt; ?1</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">GreaterThan</code></td>
 * <td><code class="code">findByAgeGreaterThan</code></td>
 * <td><code class="code">бн where x.age &gt; ?1</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">IsNull</code></td>
 * <td><code class="code">findByAgeIsNull</code></td>
 * <td><code class="code">бн where x.age is null</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">IsNotNull,NotNull</code></td>
 * <td><code class="code">findByAge(Is)NotNull</code></td>
 * <td><code class="code">бн where x.age not null</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">Like</code></td>
 * <td><code class="code">findByFirstnameLike</code></td>
 * <td><code class="code">бн where x.firstname like ?1</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">NotLike</code></td>
 * <td><code class="code">findByFirstnameNotLike</code></td>
 * <td><code class="code">бн where x.firstname not like ?1</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">OrderBy</code></td>
 * <td><code class="code">findByAgeOrderByLastnameDesc</code></td>
 * <td><code class="code">бн where x.age &gt; ?1 order by x.lastname
                desc</code></td>
 * </tr>
 * <tr>
 * <td><code class="code">Not</code></td>
 * <td><code class="code">findByLastnameNot</code></td>
 * <td><code class="code">бн where x.lastname &lt;&gt; ?1</code></td>
 * </tr>
 * </tbody>
 * </table>
 *
 */
@Generated("${generator}")
public interface ${entityType}Repository extends JpaRepository<${entityType}, ${idType}>,
		JpaSpecificationExecutor<${entityType}> {
	
}