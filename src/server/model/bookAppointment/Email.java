package server.model.bookAppointment;

/**
 * Stores an email address and checks that it is valid.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Email
{
    /** The user. */
    private String user; // case sensitive
    /** The host. */
    private String host; // case insensitive
    /** The domain. */
    private String domain; // case insensitive

    /**
     * Three argument constructor
     *
     * @param user
     *           the user part of the email address
     * @param host
     *           the host part of the email address
     * @param domain
     *           the domain part of the email address
     * @throws IllegalArgumentException
     *            if the format is wrong or if one or more of the rules listed
     *            for user, host and domain are violated.
     */
    public Email(String user, String host, String domain)
    {
        this(user + "@" + host + "." + domain);
    }

    /**
     * One argument constructor
     *
     * @param address
     *           the email address in the form: <code>user@host.domain</code>
     * @throws IllegalArgumentException
     *            if the format is wrong or if one or more of the rules listed
     *            for user, host and domain are violated.
     */
    public Email(String address)
    {
        String message = Email.legalEmailAddress(address);
        if (message != null)
        {
            throw new IllegalArgumentException(message);
        }
        String[] split = address.split("@");
        int index = split[1].lastIndexOf(".");

        this.user = split[0];
        this.host = split[1].substring(0, index);
        this.domain = split[1].substring(index + 1);
    }

    /**
     * A getter for <code>user</code>
     *
     * @return the <code>user</code>
     */
    public String getUser()
    {
        return user;
    }

    /**
     * A getter for <code>host</code>
     *
     * @return the <code>host</code>
     */
    public String getHost()
    {
        return host;
    }

    /**
     * A getter for <code>domain</code>
     *
     * @return the <code>domain</code>
     */
    public String getDomain()
    {
        return domain;
    }

    /**
     * @return the email address in the form: <code>user@host.domain</code>
     */
    public String toString()
    {
        return user + "@" + host + "." + domain;
    }

    /**
     * Comparing the email address with another object
     *
     * @param obj
     *           the comparing object
     * @return <code>true</code> if the argument is an Email address with the
     *         same user, host and domain - independent of case
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Email))
        {
            return false;
        }
        Email other = (Email) obj;
        return this.user.equalsIgnoreCase(other.user)
                && this.host.equalsIgnoreCase(other.host)
                && this.domain.equalsIgnoreCase(other.domain);
    }

    /**
     * An email address has the form: <code>user@host.domain</code> and case
     * insensitive.<br>
     * <br>
     * <code>user</code> is a dot separated list of user subparts
     * <code>userpart1.userpart2.userpart3</code> with one or more subparts. <br>
     * - Each user subpart must have between 1 and 64 characters<br>
     * - Each user subpart only contains upper- and lowercase Latin letters,
     * digits, hyphens and underscore characters.<br>
     * - The first character in <code>user</code> (in the first user subpart) has
     * to be a letter.<br>
     * <br>
     * <code>host</code> is a dot separated list of host subparts
     * <code>hostpart1.hostpart2.hostpart3</code> with one or more subparts. <br>
     * - Each host subpart must have between 1 and 63 characters<br>
     * - Each host subpart only contains upper- and lowercase Latin letters,
     * digits and and underscore characters.<br>
     * - The first character in <code>host</code> (in the first host subpart) has
     * to be a letter.<br>
     * <br>
     * <code>domain</code> is a single part with no dot characters. <br>
     * - <code>domain</code> must have between 1 and 63 characters<br>
     * - <code>domain</code> only contains upper- and lowercase Latin letters,
     * digits and and underscore characters.<br>
     * - <code>domain</code> connot contain only digits.<br>
     * - The last character in <code>domain</code> cannot be the underscore
     * character.<br>
     * The total length of <code>host</code>, the dot character and
     * <code>domain</code> cannot exceed 255 characters.<br>
     *
     * @param emailAddress
     *           the email address in the form: <code>user@host.domain</code>
     * @return <code>true</code> if all rules are followed.
     */
    public static boolean isLegal(String emailAddress)
    {
        return legalEmailAddress(emailAddress) == null;
    }

    /**
     * An email address has the form: <code>user@host.domain</code> and case
     * insensitive.<br>
     * <br>
     * <code>user</code> is a dot separated list of user subparts
     * <code>userpart1.userpart2.userpart3</code> with one or more subparts. <br>
     * - Each user subpart must have between 1 and 64 characters<br>
     * - Each user subpart only contains upper- and lowercase Latin letters,
     * digits, hyphens and underscore characters.<br>
     * - The first character in <code>user</code> (in the first user subpart) has
     * to be a letter.<br>
     * <br>
     * <code>host</code> is a dot separated list of host subparts
     * <code>hostpart1.hostpart2.hostpart3</code> with one or more subparts. <br>
     * - Each host subpart must have between 1 and 63 characters<br>
     * - Each host subpart only contains upper- and lowercase Latin letters,
     * digits and and underscore characters.<br>
     * - The first character in <code>host</code> (in the first host subpart) has
     * to be a letter.<br>
     * <br>
     * <code>domain</code> is a single part with no dot characters. <br>
     * - <code>domain</code> must have between 1 and 63 characters<br>
     * - <code>domain</code> only contains upper- and lowercase Latin letters,
     * digits and and underscore characters.<br>
     * - <code>domain</code> connot contain only digits.<br>
     * - The last character in <code>domain</code> cannot be the underscore
     * character.<br>
     * The total length of <code>host</code>, the dot character and
     * <code>domain</code> cannot exceed 255 characters.<br>
     *
     * @param address
     *           the email address in the form: <code>user@host.domain</code>
     * @return <code>null</code> if all rules are followed. Otherwise return a
     *         string with a proper error message:<br>
     *         "No @ sign"<br>
     *         "To many @ signs"<br>
     *         "Cannot start or end with the @ sign"<br>
     *         "User part must be between 1 and 64 characters"<br>
     *         "User part cannot end with the dot character"<br>
     *         "User part cannot contain two consecutive dot characters"<br>
     *         "First character in user has to be a letter"<br>
     *         "User part cannot contain other charcters than letters, digits, underscores, hyphens and dots"<br>
     *         "Host and domain are longer than 255 characters"<br>
     *         "Missing dot between host and domain"<br>
     *         "Domain part must be between 1 and 63 characters"<br>
     *         "Domain part cannot end with an underscore"<br>
     *         "Domain part cannot be all digits"<br>
     *         "Domain part cannot contain other charcters than letters, digits and underscores"<br>
     *         <br>
     */
    public static String legalEmailAddress(String address)
    {
        if (address == null || address.isBlank())
        {
            return "Email cannot be empty.";
        }

        String[] split = address.split("@");
        if (split.length < 2)
        {
            return "Email: No @ sign";
        }
        if (split.length > 2)
        {
            return "Email: To many @ signs";
        }
        if (address.startsWith("@") || address.endsWith("@"))
        {
            return "Email: Cannot start or end with the @ sign";
        }
        String user = split[0];

        if (user.length() < 1 || user.length() > 63)
        {
            return "Email: User part must be between 1 and 64 characters";
        }

        if (user.endsWith("."))
        {
            return "Email: User part cannot end with the dot character";
        }

        if (user.contains(".."))
        {
            return "Email: User part cannot contain two consecutive dot characters";
        }

        if (user.length() > 0 && !Character.isAlphabetic(user.charAt(0)))
        {
            return "Email: First character in user has to be a letter";
        }

        if (!user.matches("^([a-zA-Z0-9_-][\\.]*)*$"))
        {
            return "Email: User part cannot contain other charcters than letters, digits, undercores, hyphens and dots";
        }

        String hostAndDomain = split[1];

        if (hostAndDomain.length() > 255)
        {
            return "Email: Host and domain are longer than 255 characters";
        }

        int index = hostAndDomain.lastIndexOf(".");
        if (index == -1)
        {
            return "Email: Missing dot between host and domain";
        }
        String host = hostAndDomain.substring(0, index);
        String domain = hostAndDomain.substring(index + 1);

        if (domain.length() < 1 || domain.length() > 63)
        {
            return "Email: Domain part must be between 1 and 63 characters";
        }

        if (domain.endsWith("_"))
        {
            return "Email: Domain part cannot end with an underscore";
        }

        if (domain.matches("^[0-9]*$"))
        {
            return "Email: Domain part cannot be all digits";
        }

        if (!domain.matches("^([a-zA-Z0-9_][\\.]*)*$"))
        {
            return "Email: Domain part cannot contain other charcters than letters, digits and underscores";
        }

        if (host.length() > 0 && !Character.isAlphabetic(host.charAt(0)))
        {
            return "Email: First character in host has to be a letter";
        }

        String[] hostSplit = host.split(".");
        for (int i = 0; i < hostSplit.length; i++)
        {
            if (hostSplit[i].length() < 1 || hostSplit[i].length() > 63)
            {
                return "Email: The lenght of the dot separated host parts has to be between 1 and 63";
            }
            if (hostSplit[i].charAt(0) == '_')
            {
                return "Email: First character after a dot in host cannot be an underscore";
            }
            if (!hostSplit[i].matches("^[a-zA-Z0-9_]*$"))
            {
                return "Email: Host part cannot contain other charcters than letters, digits, underscores and dots";
            }
        }
        if (host.endsWith("_"))
        {
            return "Email: Host part cannot end with an underscore";
        }

        if (domain.endsWith("_"))
        {
            return "Email: Domain part cannot end with an underscore";
        }

        if (domain.matches("^[0-9]*$"))
        {
            return "Email: Domain part cannot be all digits";
        }

        if (!domain.matches("^[a-zA-Z0-9_]*$"))
        {
            return "Email: Domain part cannot contain other charcters than letters, digits and undercores";
        }

        return null;
    }

}
