package priv.eternasparkle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eternasparkle.entity.Dept;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.service.DeptService;
import priv.eternasparkle.service.UserService;
import priv.eternasparkle.util.R;
import priv.eternasparkle.vo.DeptListVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门控制器
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@RestController
@RequestMapping("/System/Dept")
public class DeptController {
    /**
     * 部门服务
     */
    private final DeptService deptService;
    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 部门控制器
     *
     * @param deptService 部门服务
     * @param userService 用户服务
     */
    public DeptController(DeptService deptService, UserService userService) {
        this.deptService = deptService;
        this.userService = userService;
    }

    /**
     * 列表部门
     *
     * @return {@link R }
     */
    @GetMapping("/listDept")
    public R listDept() {
        List<Dept> depts = deptService.list();
        List<User> users = userService.list();

        Map<Integer, List<User>> deptUserMap = users.stream()
                .filter(user -> user.getDeptId() != null)
                .collect(Collectors.groupingBy(User::getDeptId));

        Map<Integer, String> leaderNameMap = users.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));

        List<DeptListVO> tree = buildDeptTree(depts, "0", deptUserMap, leaderNameMap);
        return R.ok(tree);
    }

    /**
     * 构建部门树
     *
     * @param depts         部门
     * @param parentId
     * @param deptUserMap   部门用户地图
     * @param leaderNameMap Leader Name 映射
     * @return {@link List }<{@link DeptListVO }>
     */
    private List<DeptListVO> buildDeptTree(List<Dept> depts, String parentId, Map<Integer, List<User>> deptUserMap, Map<Integer, String> leaderNameMap) {
        return depts.stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    DeptListVO deptListVO = new DeptListVO();
                    deptListVO.setId(dept.getId());
                    deptListVO.setDeptName(dept.getDeptName());
                    deptListVO.setLeaderId(dept.getLeaderId());
                    String leaderName = leaderNameMap.getOrDefault(dept.getLeaderId(), "Unknown");
                    deptListVO.setLeaderName(leaderName);
                    Integer deptId = Integer.parseInt(dept.getId());
                    List<User> deptUsers = deptUserMap.getOrDefault(deptId, List.of());
                    System.out.println(deptUsers);
                    deptListVO.setDeptUserCount(deptUsers.size());
                    deptListVO.setChildren(buildDeptTree(depts, dept.getId(), deptUserMap, leaderNameMap));
                    return deptListVO;
                })
                .collect(Collectors.toList());
    }
}
