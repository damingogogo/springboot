import Layout from '../layout'
import MainView from '../layout/components/MainView'

/**
 * hidden: true                  如果设置为 true，该项菜单将不会显示在菜单栏中(默认为 false)
 * meta : {
    title: 'title'               菜单名
    icon: 'icon-name'            图标名
    fixed: true                  如果设置为 true，该项 tag 将一直存在 tag 栏中(默认为 false)
  }
 * */

export const asyncRoutes = [
  {
    path: '/school',
    name: 'School',
    component: Layout,
    redirect: '/school/teacher',
    meta: {
      title: '学校事务管理',
      icon: 'vue-dsn-icon-menu'
    },
    children: [
      {
        path: '/user',
        name: 'User',
        component: MainView,
        redirect: '/school/user/user1',
        meta: {
          title: '系统用户管理'
        },
        children: [

          {
            path: '/user1',
            name: 'User1',
            component: () => import('../views/school/User1'),
            meta: {
              title: '系统用户管理',
              icon: 'el-icon-user'

            },
          },

          {
            path: '/user2',
            name: 'User2',
            component: () => import('../views/school/User2'),
            meta: {
              title: '普通用户管理',
              icon: 'el-icon-user'

            }

          }
        ]

      },
      {
        path: 'student',
        name: 'Student',
        component: () => import('../views/school/Student'),
        meta: {
          title: '学生管理',
          icon: 'el-icon-menu'
        }
      },
      {
        path: 'parent',
        name: 'Parent',
        component: () => import('../views/school/Parent'),
        meta: {
          title: '家长管理',
          icon: 'el-icon-menu'
        }
      },
      {
        path: 'teacher',
        name: 'Teacher',
        component: () => import('../views/school/Teacher'),
        meta: {
          title: '教师管理',
          icon: 'el-icon-menu'
        }
      },
      {
        path: 'school',
        name: 'School',
        component: () => import('../views/school/School'),
        meta: {
          title: '学校管理',
          icon: 'el-icon-setting'
        }
      }
      ,
      {
        path: 'grade',
        name: 'Grade',
        component: () => import('../views/school/Grade'),
        meta: {
          title: '年级管理',
          icon: 'el-icon-setting'
        }
      }
      ,
      {
        path: 'class',
        name: 'Class',
        component: () => import('../views/school/Class'),
        meta: {
          title: '班级管理',
          icon: 'el-icon-setting'
        }
      }
      ,
      {
        path: 'subject',
        name: 'Subject',
        component: () => import('../views/school/Subject'),
        meta: {
          title: '科目管理',
          icon: 'el-icon-setting'
        }
      }
      ,
      {
        path: 'score',
        name: 'Score',
        component: () => import('../views/school/Score'),
        meta: {
          title: '成绩管理',
          icon: 'el-icon-setting'
        }
      }
    ]
  },
  {
    path: '/notice',
    name: 'Notice',
    component: Layout,
    redirect: '/notice/inform',
    meta: {
      title: '公告管理',
      icon: 'el-icon-date'
    },
    children: [
      {
        path: 'inform',
        name: 'Inform',
        component: () => import('../views/school/Inform'),
        meta: {
          title: '公告管理'
        }

      }
      // },
      // {
      //   path: 'receive',
      //   name: 'Receive',
      //   component: () => import('../views/school/Receive'),
      //   meta: {
      //     title: '回应型公告'
      //   }
      // }
    ]
  },
  {
    path: '/vote',
    name: 'Vote',
    component: Layout,
    redirect: '/vote/vote1',
    meta: {
      title: '投票管理',
      icon: 'el-icon-loading'
    },
    children: [
      {
        path: 'vote1',
        name: 'Vote1',
        component: () => import('../views/vote/Vote'),
        meta: {
          title: '投票管理'
        }
      }
    ]
  },
  {
    path: '/sign',
    name: 'Sign',
    component: Layout,
    redirect: '/sign/sign1',
    meta: {
      title: '报名管理',
      icon: 'el-icon-phone-outline'
    },
    children: [
      {
        path: 'sign1',
        name: 'Sign1',
        component: () => import('../views/sign/Sign'),
        meta: {
          title: '报名管理'
        }
      }
    ]
  },
  {
    path: '/form',
    name: 'Form',
    component: Layout,
    redirect: '/form/form1',
    meta: {
      title: '填表管理',
      icon: 'el-icon-edit'
    },
    children: [
      {
        path: 'form1',
        name: 'Form1',
        component: () => import('../views/form/Form1'),
        meta: {
          title: '填表管理'
        }
      }

    ]
  }
]
