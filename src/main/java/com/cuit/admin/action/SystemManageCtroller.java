package com.cuit.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminMenu;
import com.cuit.admin.bean.AdminRole;
import com.cuit.admin.service.SystemManageSer;
import com.cuit.web.bean.PagerInfo;
import com.cuit.web.bean.PagerStruct;
import com.cuit.web.bean.RequestResult;
import com.cuit.web.security.MySecurityMetadataSource;
import com.cuit.web.util.EncryptUtil;
import com.cuit.web.util.RequestParseUtil;
import com.cuit.web.util.RequestSessionUtil;

@Controller
@RequestMapping(value = "/admin/system")
public class SystemManageCtroller {
    Logger logger = Logger.getLogger(SystemManageCtroller.class);
    @Autowired
    private SystemManageSer systemManageSer;

    private MySecurityMetadataSource mySecurityMetadataSource;

    /**
     * 页面跳转，将参数传递到页面解析
     * @param request
     * @param pageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        return new ModelAndView("/admin/system/" + pageName, RequestSessionUtil.getRequestParamData(request));
    }

    /******************************* 后台用户管理 *****************************************/
    /**
     * 获取登录用户的菜单列表
     * @param request
     * @param authentication
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAdminUserMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminMenu> getAdminUserMenus(HttpServletRequest request, Authentication authentication) {
        List<AdminMenu> adminMenus = null;
        try {
            AdminInfo adminUser = ((AdminInfo) authentication.getPrincipal());
            adminMenus = this.systemManageSer.getAdminUserMenus(adminUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return adminMenus;
    }

    /**
     * 修改后台管理用户密码
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param authentication
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateAdminPassword", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> updateAdminPassword(HttpServletRequest request, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
                                                     Authentication authentication) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            AdminInfo admin = ((AdminInfo) authentication.getPrincipal());
            this.systemManageSer.updateAdminPassword(admin, oldPassword, newPassword);
        } catch (Exception e) {
            result.setCode(1);
            e.printStackTrace();
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 获取后台管理用户列表（分页）
     * @param request
     * @param size
     * @param page
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getAdminUserList", method = RequestMethod.POST)
    @ResponseBody
    public PagerStruct<AdminInfo> getAdminUserList(HttpServletRequest request, @RequestParam("limit") String size, @RequestParam("page") String page, @RequestParam("keyWord") String keyWord) {
        PagerStruct<AdminInfo> result = new PagerStruct<AdminInfo>();
        PagerInfo pagerParam = new PagerInfo();
        pagerParam.setPage(Integer.parseInt(page));
        pagerParam.setSize(Integer.parseInt(size));
        result.setPage(Integer.parseInt(page));
        try {
            result.setRows(this.systemManageSer.getAdminUserList(keyWord, pagerParam));
            result.setTotal(this.systemManageSer.getAdminUserListCnt(keyWord));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取管理员分页数据出错");
        }
        return result;
    }

    @RequestMapping(value = "/getAdminUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public AdminInfo getAdminUserInfo(HttpServletRequest request, @RequestParam("userId") String userId) {
        AdminInfo adminInfo = null;
        try {
            adminInfo = this.systemManageSer.getAdminUserInfo(userId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取管理员:" + userId + "数据出错");
        }
        return adminInfo;
    }

    @RequestMapping(value = "/addOrUpdateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> addOrUpdateAdmin(HttpServletRequest request, @RequestParam("jParam") String jParam, @RequestParam("roles") String rolesStr) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            AdminInfo adminUpdated = RequestParseUtil.parseRequestJsonDataToObject(jParam, AdminInfo.class);
            List<AdminRole> roles = RequestParseUtil.parseRequestJsonDataToList(rolesStr, AdminRole[].class);
            adminUpdated.setRoles(roles);

            if (adminUpdated.getUserId() == null) {
                adminUpdated.setUserPass(EncryptUtil.MD5Encode(adminUpdated.getUserPass()));
                this.systemManageSer.addAdminUser(adminUpdated);
            } else {
                AdminInfo oldAdmin = this.systemManageSer.getAdminUserInfo(adminUpdated.getUserId().toString());
                if (!oldAdmin.getUserPass().equals(adminUpdated.getUserPass())) {
                    adminUpdated.setUserPass(EncryptUtil.MD5Encode(adminUpdated.getUserPass()));
                }
                this.systemManageSer.updateAdminUser(adminUpdated);
            }
        } catch (Exception e) {
            result.setCode(1);
            e.printStackTrace();
            result.setMessage("保存用户失败");
        }
        return result;
    }

    @RequestMapping(value = "/getAdminMenu", method = RequestMethod.POST)
    @ResponseBody
    public AdminMenu getAdminMenu(@RequestParam("menuNo") String menuNo) {
        AdminMenu menu = new AdminMenu();
        try {
            Long no = Long.parseLong(menuNo);
            menu = this.systemManageSer.getAdminMenu(no);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return menu;
    }

    @RequestMapping(value = "/addOrUpdateAdminMenu", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> addOrUpdateAdminMenu(@RequestParam("jParam") String jsonObject) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            AdminMenu adminMenu = RequestParseUtil.parseRequestJsonDataToObject(jsonObject, AdminMenu.class);
            if (adminMenu.getNo() == null || adminMenu.getNo() == 0) {
                this.systemManageSer.addAdminMenu(adminMenu);
            } else {
                this.systemManageSer.updateAdminMenu(adminMenu);
            }
//            this.mySecurityMetadataSource.loadResourceDefine();
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(1000);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getAllAdminMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminMenu> getAllAdminMenus() {
        List<AdminMenu> allMenusList = null;
        try {
            allMenusList = this.systemManageSer.getAllAdminMenus();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return allMenusList;
    }

    @RequestMapping(value = "/getAdminMenuList", method = RequestMethod.POST)
    @ResponseBody
    public PagerStruct<AdminMenu> getAdminMenuList(@RequestParam("keyWord") String keyWord, @RequestParam("limit") String size, @RequestParam("page") String page) {
        PagerStruct<AdminMenu> menuResult = new PagerStruct<AdminMenu>();
        PagerInfo pagerParam = new PagerInfo();
        pagerParam.setPage(Integer.parseInt(page));
        pagerParam.setSize(Integer.parseInt(size));

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyWord", keyWord);

            menuResult.setRows(this.systemManageSer.getAdminMenuList(params, pagerParam));
            menuResult.setTotal(this.systemManageSer.getAdminMenuListCnt(params));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return menuResult;
    }

    @RequestMapping(value = "/addOrUpdateAdminRole", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> addOrUpdateAdminRole(@RequestParam("jParam") String jsonObject, @RequestParam("jGrant") String jGrant) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            AdminRole adminRole = RequestParseUtil.parseRequestJsonDataToObject(jsonObject, AdminRole.class);
            List<AdminGrant> grants = RequestParseUtil.parseRequestJsonDataToList(jGrant, AdminGrant[].class);
            if (adminRole.getNo() == null || adminRole.getNo() == 0) {
                this.systemManageSer.addAdminRole(adminRole);
            } else {
                this.systemManageSer.updateAdminRole(adminRole);
            }
            adminRole.setGrants(grants);
            this.systemManageSer.updateAdminRoleGrants(adminRole);
            this.mySecurityMetadataSource.loadResourceDefine();
        } catch (Exception e) {
            result.setCode(1000);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getAdminRole", method = RequestMethod.POST)
    @ResponseBody
    public AdminRole getAdminRole(@RequestParam("roleNo") String role) {
        AdminRole adminRole = new AdminRole();
        try {
            Long no = Long.parseLong(role);
            adminRole = this.systemManageSer.getAdminRole(no);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return adminRole;
    }

    @RequestMapping(value = "/getAdminGrantsByRole", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminGrant> getAdminGrantsByRole(@RequestParam("roleNo") String roleNo) {
        List<AdminGrant> adminGrants = null;
        try {
            AdminRole admin = new AdminRole();
            admin.setNo(Long.parseLong(roleNo));
            adminGrants = this.systemManageSer.getAdminGrantsByRole(admin);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return adminGrants;
    }

    @RequestMapping(value = "/getAllAdminRoles", method = RequestMethod.POST)
    @ResponseBody
    public List<AdminRole> getAllAdminRoles() {
        List<AdminRole> allRoleList = null;
        try {
            allRoleList = this.systemManageSer.getAllAdminRoles();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return allRoleList;
    }

    @RequestMapping(value = "/getAdminRoleList", method = RequestMethod.POST)
    @ResponseBody
    public PagerStruct<AdminRole> getAdminRoleList(@RequestParam("keyWord") String keyWord, @RequestParam("limit") String size, @RequestParam("page") String page) {
        PagerStruct<AdminRole> menuResult = new PagerStruct<AdminRole>();
        PagerInfo pagerParam = new PagerInfo();
        pagerParam.setPage(Integer.parseInt(page));
        pagerParam.setSize(Integer.parseInt(size));

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyWord", keyWord);

            menuResult.setRows(this.systemManageSer.getAdminRoleList(params, pagerParam));
            menuResult.setTotal(this.systemManageSer.getAdminRoleListCnt(params));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return menuResult;
    }

}
