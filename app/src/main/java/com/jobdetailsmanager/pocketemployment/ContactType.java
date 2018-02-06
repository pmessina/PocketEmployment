package com.jobdetailsmanager.pocketemployment;

public enum ContactType
{
    RECRUITER(0, "Recruiter"),
    ACCOUNT_MANAGER(1, "Account Manager"),
    PROJECT_MANAGER(2, "Project Manager"),
    DEVELOPER(3, "Developer");

    private int value;

    private String label;

    ContactType(int value, String label)
    {
        this.value = value;
        this.label = label;
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return label;
    }
}
