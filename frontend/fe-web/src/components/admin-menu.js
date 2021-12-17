export const menuItems = [
    {
        id: 1,
        label: "menuitems.menu.text",
        isTitle: true
    },
    {
        id: 2,
        label: "menuitems.dashboards.text",
        icon: "bx-home-circle",
        link: "/admin",
        
    },
    {
        id: 7,
        isLayout: true
    },
    {
        id: 8,
        label: "menuitems.apps.text",
        isTitle: true
    },
    
    {
        id: 42,
        label: "menuitems.tasks.text",
        icon: "bx-task",
        subItems: [
            {
                id: 43,
                label: "menuitems.tasks.list.tasklist",
                link: "/tasks/list",
                parentId: 42
            },
            {
                id: 44,
                label: "menuitems.tasks.list.kanban",
                link: "/tasks/kanban",
                parentId: 42
            },
            {
                id: 45,
                label: "menuitems.tasks.list.createtask",
                link: "/tasks/create",
                parentId: 42
            }
        ]
    },
    {
        id: 46,
        label: "menuitems.contacts.text",
        icon: "bxs-user-detail",
        subItems: [
            {
                id: 47,
                label: "menuitems.contacts.list.usergrid",
                link: "/contacts/grid",
                parentId: 46
            },
            {
                id: 48,
                label: "menuitems.contacts.list.userlist",
                link: "/contacts/list",
                parentId: 46
            },
            {
                id: 49,
                label: "menuitems.contacts.list.profile",
                link: "/contacts/profile",
                parentId: 46
            }
        ]
    },
    {
        id: 50, 
        label: "menuitems.blog.text",
        icon: "bx-detail",
        badge: {
            variant: "success",
            text: "menuitems.blog.badge"
        },
        subItems: [
            {
                id: 51,
                label: 'menuitems.blog.list.bloglist',
                link: '/blog/list',
                parentId: 50
            },
            {
                id: 52,
                label: 'menuitems.blog.list.grid',
                link: '/blog/grid',
                parentId: 50
            },
            {
                id: 53,
                label: 'menuitems.blog.list.detail',
                link: '/blog/detail',
                parentId: 50
            }
        ]
    },
    {
        id: 54,
        label: "menuitems.pages.text",
        isTitle: true
    },
    {
        id: 55,
        label: "menuitems.authentication.text",
        icon: "bx-user-circle",
        badge: {
            variant: "success",
            text: "menuitems.authentication.badge"
        },
        subItems: [
            {
                id: 56,
                label: "menuitems.authentication.list.login",
                link: "/auth/login-1",
                parentId: 55
            },
            {
                id: 57,
                label: "menuitems.authentication.list.login-2",
                link: "/auth/login-2",
                parentId: 55
            },
            {
                id: 58,
                label: "menuitems.authentication.list.register",
                link: "/auth/register-1",
                parentId: 55
            },
            {
                id: 59,
                label: "menuitems.authentication.list.register-2",
                link: "/auth/register-2",
                parentId: 55
            },
            {
                id: 60,
                label: "menuitems.authentication.list.recoverpwd",
                link: "/auth/recoverpwd",
                parentId: 55
            },
            {
                id: 61,
                label: "menuitems.authentication.list.recoverpwd-2",
                link: "/auth/recoverpwd-2",
                parentId: 55
            },
            {
                id: 62,
                label: "menuitems.authentication.list.lockscreen",
                link: "/auth/lock-screen",
                parentId: 55
            },
            {
                id: 63,
                label: "menuitems.authentication.list.lockscreen-2",
                link: "/auth/lock-screen-2",
                parentId: 55
            },
            {
                id: 64,
                label: "menuitems.authentication.list.confirm-mail",
                link: "/auth/confirm-mail",
                parentId: 55
            },
            {
                id: 65,
                label: "menuitems.authentication.list.confirm-mail-2",
                link: "/auth/confirm-mail-2",
                parentId: 55
            },
            {
                id: 66,
                label: "menuitems.authentication.list.verification",
                link: "/auth/email-verification",
                parentId: 55
            },
            {
                id: 67,
                label: "menuitems.authentication.list.verification-2",
                link: "/auth/email-verification-2",
                parentId: 55
            },
            {
                id: 68,
                label: "menuitems.authentication.list.verification-step",
                link: "/auth/two-step-verification",
                parentId: 55
            },
            {
                id: 69,
                label: "menuitems.authentication.list.verification-step-2",
                link: "/auth/two-step-verification-2",
                parentId: 55
            }
        ]
    },
    {
        id: 70,
        label: "menuitems.utility.text",
        icon: "bx-file",
        subItems: [
            {
                id: 71,
                label: "menuitems.utility.list.starter",
                link: "/pages/starter",
                parentId: 70
            },
            {
                id: 72,
                label: "menuitems.utility.list.maintenance",
                link: "/pages/maintenance",
                parentId: 70
            },
            {
                id: 72,
                label: "menuitems.utility.list.comingsoon",
                link: "/pages/coming-soon",
                parentId: 70
            },
            {
                id: 73,
                label: "menuitems.utility.list.timeline",
                link: "/pages/timeline",
                parentId: 70
            },
            {
                id: 74,
                label: "menuitems.utility.list.faqs",
                link: "/pages/faqs",
                parentId: 70
            },
            {
                id: 75,
                label: "menuitems.utility.list.pricing",
                link: "/pages/pricing",
                parentId: 70
            },
            {
                id: 76,
                label: "menuitems.utility.list.error404",
                link: "/pages/404",
                parentId: 70
            },
            {
                id: 77,
                label: "menuitems.utility.list.error500",
                link: "/pages/500",
                parentId: 70
            }
        ]
    },
   
];

